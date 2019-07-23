package com.pr1zrak.crud;

import com.loginbox.dropwizard.mybatis.MybatisBundle;
import com.pr1zrak.crud.db.UserDAO;
import com.pr1zrak.crud.db.UserDAOImpl;
import com.pr1zrak.crud.mappers.UserMapper;
import com.pr1zrak.crud.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSessionFactory;

public class CrudMybatisApplication extends Application<CrudMybatisConfiguration> {

    private final MybatisBundle<CrudMybatisConfiguration> mybatisBundle
            = new MybatisBundle<CrudMybatisConfiguration>(UserMapper.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(CrudMybatisConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private final MigrationsBundle<CrudMybatisConfiguration> migrationsBundle
            = new MigrationsBundle<CrudMybatisConfiguration>() {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(CrudMybatisConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new CrudMybatisApplication().run(args);
    }

    @Override
    public String getName() {
        return "CrudMybatis";
    }

    @Override
    public void initialize(final Bootstrap<CrudMybatisConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(mybatisBundle);
        bootstrap.addBundle(migrationsBundle);
    }

    @Override
    public void run(final CrudMybatisConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

        final SqlSessionFactory sessionFactory = mybatisBundle.getSqlSessionFactory();
        final UserDAO userDAO
                = new UserDAOImpl(sessionFactory);

        environment.jersey().register(
                new UserResource(userDAO)
        );
    }

}
