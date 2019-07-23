package com.pr1zrak.crud;

import com.loginbox.dropwizard.mybatis.MybatisBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.ibatis.session.SqlSessionFactory;

public class CrudMybatisApplication extends Application<CrudMybatisConfiguration> {

    private final MybatisBundle<CrudMybatisConfiguration> mybatisBundle
            = new MybatisBundle<CrudMybatisConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(CrudMybatisConfiguration configuration) {
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
    }

    @Override
    public void run(final CrudMybatisConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

        SqlSessionFactory sessionFactory = mybatisBundle.getSqlSessionFactory();

//        environment.jersey().register(
//                new BookmarksResource(sessionFactory)
//        );
    }

}
