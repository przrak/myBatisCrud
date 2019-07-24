package com.pr1zrak.crud;

import com.codahale.metrics.jdbi3.strategies.TimedAnnotationNameStrategy;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.loginbox.dropwizard.mybatis.MybatisBundle;
import com.pr1zrak.crud.db.UserDAO;
import com.pr1zrak.crud.db.UserDAOImpl;
import com.pr1zrak.crud.mappers.UserMapper;
import com.pr1zrak.crud.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jdbi.v3.core.Jdbi;

import java.io.IOException;

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
                    final Environment environment) throws IOException {
        // TODO: implement application

        Jdbi dbi = new JdbiFactory(new TimedAnnotationNameStrategy()).build(environment,
                configuration.getDataSourceFactory(), "h2");
        dbi.useTransaction(h -> {
            h.createScript(Resources.toString(Resources.getResource("init_hsqldb.sql"), Charsets.UTF_8)).execute();
            h.createScript(Resources.toString(Resources.getResource("populate_hsqldb.sql"), Charsets.UTF_8)).execute();
        });

        final SqlSessionFactory sessionFactory = mybatisBundle.getSqlSessionFactory();
        final UserDAO userDAO
                = new UserDAOImpl(sessionFactory);

        environment.jersey().register(
                new UserResource(userDAO)
        );
    }

}
