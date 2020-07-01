package com.bfchengnuo.uselibraries.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.bfchengnuo.uselibraries.common.User;

import java.util.List;
import java.util.Map;

/**
 * 基于 POI 的封装
 * 高版本 excel 建议导入 poi-ooxml
 * 如需使用 sax 读取，导入 xercesImpl
 *
 * @author 冰封承諾Andy
 * @date 2020/6/29
 */
public class ExcelDemo {
    public static void main(String[] args) {
        // 可从 cp 中读取
        ExcelReader reader = ExcelUtil.getReader(ResourceUtil.getStream("aaa.xlsx"));
        // ExcelReader reader = ExcelUtil.getReader(FileUtil.file("aaa.xlsx"));

        // 通过sheet编号获取
        reader = ExcelUtil.getReader(FileUtil.file("test.xlsx"), 0);
        // 通过sheet名获取
        reader = ExcelUtil.getReader(FileUtil.file("test.xlsx"), "sheet1");
    }

    private static void reader() {
        ExcelReader reader1 = ExcelUtil.getReader("d:/aaa.xlsx");
        List<List<Object>> readAll = reader1.read();

        ExcelReader reader2 = ExcelUtil.getReader("d:/aaa.xlsx");
        List<Map<String,Object>> readAllMap = reader2.readAll();

        ExcelReader reader3 = ExcelUtil.getReader("d:/aaa.xlsx");
        List<User> all = reader3.readAll(User.class);
    }

    /**
     * 其他例如自定义样式相关
     * see：https://www.hutool.cn/docs/#/poi/Excel%E7%94%9F%E6%88%90-ExcelWriter
     */
    private static void writer() {
        // 构建数据
        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);
        // 或者使用 map 构建
        // ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1Map, row2Map);
        // 使用 bean 构建
        // List<User> rows = CollUtil.newArrayList(bean1, bean2);

        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
        //通过构造方法创建writer
        //ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

        // 自定义标题别名（Bean 为数据）
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("score", "分数");

        //跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();
        //合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");

        //一次性写出内容，强制输出标题
        writer.write(rows, true);
        //out为OutputStream，需要写出到的目标流
        // writer.flush(out);

        //关闭writer，释放内存
        writer.close();

    }
}
