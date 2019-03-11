
package com.xshwd.user.config.mybatis;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
//@MapperScan({"com.xshwd.orm.user.mapper","cc.onion.minibuyker.user.ggpo.mapper","cc.onion.minibuyker.user.offline.mapper",
//    "cc.onion.minibuyker.user.offline.category.mapper","cc.onion.minibuyker.user.poster.mapper"})
@MapperScan({"com.xshwd.orm.user.mapper","com.xshwd.orm.product.mapper"})
public class MybatisPlusConfig {

    @SuppressWarnings("unused")
    private final Logger log = LoggerFactory.getLogger(MybatisPlusConfig.class);


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader, GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
//        sqlSessionFactory.setTypeAliasesPackage("cc.onion.minibuyker.user.mapper,cc.onion.minibuyker.user.ggpo.mapper,cc.onion.minibuyker.user.offline.mapper,cc.onion.minibuyker.user.offline.category.mapper");
        sqlSessionFactory.setTypeAliasesPackage("com.xshwd.orm.user.mapper,com.xshwd.orm.product.mapper");


        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

//        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:cc/onion/minibuyker/user/**/mapper/xml/*.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:com/xshwd/orm/**/mapper/xml/*.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();

//        globalConfiguration.setMetaObjectHandler(new MyMetaObjectHandler());
//
//        globalConfiguration.setLogicDeleteValue("status");
//
//
//        globalConfiguration.setLogicNotDeleteValue("1");

        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        PaginationInterceptor pagination = new PaginationInterceptor();
        sqlSessionFactory.setPlugins(new Interceptor[]{
                pagination,
                new PerformanceInterceptor(),
                new OptimisticLockerInterceptor(),
                new com.baomidou.mybatisplus.plugins.SqlExplainInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {

        return new OptimisticLockerInterceptor();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("status");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(2);

        conf.setMetaObjectHandler(new MyMetaObjectHandler());

        return conf;
    }

}
