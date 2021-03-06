package com.github.core.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author <a href="http://github.com/athc">dujf</a>
 * @date 2018/11/8
 * @since JDK1.8
 */
public class MybatisPlusUtils {

  public static void main(String[] args) {


    //用来获取xxx.properties文件的配置信息
    AutoGenerator mpg = new AutoGenerator();
    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir("/Users/dujf/Downloads/code");
    gc.setFileOverride(true);
    gc.setActiveRecord(true);
    // XML 二级缓存
    gc.setEnableCache(false);
    // XML ResultMap
    gc.setBaseResultMap(true);
    // XML columList
    gc.setBaseColumnList(false);
    //作者
    gc.setAuthor("dujf");

    // 自定义文件命名，注意 %s 会自动填充表实体属性！
    gc.setMapperName("%sMapper");
    gc.setServiceName("%sService");
    gc.setServiceImplName("%sServiceImpl");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setTypeConvert(new MySqlTypeConvert() {
      // 自定义数据库表字段类型转换【可选】
      @Override
      public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        System.out.println("转换类型：" + fieldType);
        // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
        return (DbColumnType) super.processTypeConvert(gc, fieldType);
      }
    });
    // 数据源配置
    dsc.setDbType(DbType.MYSQL);
    dsc.setTypeConvert(new MySqlTypeConvert());
    dsc.setDriverName("com.mysql.jdbc.Driver");
    dsc.setUsername("root");
    dsc.setPassword("123456");
    dsc.setUrl("jdbc:mysql://127.0.0.1:3306/shiro_manage?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true");
    mpg.setDataSource(dsc);


    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
    // 此处可以修改为您的表前缀
//        strategy.setTablePrefix(new String[]{"tlog_", "tsys_"});
    // 表名生成策略
    strategy.setNaming(NamingStrategy.underline_to_camel);
    // 需要生成的表
    strategy.setInclude();
    // 排除生成的表
    // strategy.setExclude(new String[]{"test"});
    mpg.setStrategy(strategy);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setParent("com.github.oauth2");
    pc.setController("controller");
    pc.setEntity("model");
    pc.setMapper("mapper");
    pc.setService("service");
    pc.setServiceImpl("service");
    pc.setXml("");
    //pc.setModuleName("test");
    mpg.setPackageInfo(pc);

    // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
        this.setMap(map);
      }
    };

    // 自定义 xxList.jsp 生成
    List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
    // 调整 xml 生成目录演示
    focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return "/Users/dujf/Downloads/code/xml/" + tableInfo.getEntityName() + "Mapper.xml";
      }
    });
    // 关闭默认 xml 生成，调整生成 至 根目录
    TemplateConfig tc = new TemplateConfig();
    tc.setXml(null);
    mpg.setTemplate(tc);

    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);
    // 执行生成
    mpg.execute();
  }

}
