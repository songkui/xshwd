package com.xshwd.framework.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Auther: SK
 * @Date: 2019/3/2 11:38
 * @Description:
 *  mybatis-plus 逆向工程生成实体类、mapper、xml等代码工具类
 *  填写项目路径、表名、模块名、开发人员名即可
 *  使用时才放开注释，使用完注释掉main方法
 */
public class CodeGenerator {

    //模块名 本项目对应库名
    private static String moudleName = "xshwd_user";
    //需要生成代码的表
    private static String[] tableName = new String[] { "acc_wx_user"};
    //项目路径
    private static String outPutDir = "D:/TABLE_ORM";
    private static String author = "SK";


    private static String packageParent = "com.xshwd.orm.user";
    //测试环境数据库配置
    private static String dataBaseUserName = "root";
    private static String password = "258";
    private static String url = "jdbc:mysql://127.0.0.1:3306/xshwd_user?useSSL=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";


   public static void main(String[] args) {



        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor(author);
        gc.setOutputDir(outPutDir);
        gc.setFileOverride(false);// 是否覆盖同名文件，默认是false
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceImpl");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(dataBaseUserName);
        dsc.setPassword(password);
        dsc.setUrl(url);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //表前缀忽略
        strategy.setTablePrefix(new String[] { "tb_" });
        //_转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setDbColumnUnderline(true);
        strategy.setInclude(tableName);
        strategy.setSuperEntityClass("cc.onion.minibuyker.core.entity.BaseEntity");
        strategy.setSuperEntityColumns(new String[] { "idx", "version","status","create_time","update_time" });
        //乐观锁字段
        //strategy.setVersionFieldName("version");
        //添加实体类注解
        strategy.entityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageParent);
        pc.setModuleName(moudleName);
        mpg.setPackageInfo(pc);
        //模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        //不生成controller
        templateConfig.setController(null);
        mpg.setTemplate(templateConfig);

        // 执行生成
        mpg.execute();


    }
}
