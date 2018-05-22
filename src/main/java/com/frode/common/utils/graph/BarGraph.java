package com.frode.common.utils.graph;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BarGraph{


    public  static String getBarchart(int csPaper,int csProject,int csPrize,int csPatent,int sePaper,int seProject,int sePrize,int sePatent)  {
        final String cs = "计科";//
        final String se = "软件工程";
        final String paper = "论文";
        final String project = "项目";
        final String prize = "奖项";
        final String patent = "专利";
        final DefaultCategoryDataset dataset =
                new DefaultCategoryDataset( );
        //先测试静态数据
        dataset.addValue( csPaper , cs , paper);
        dataset.addValue(csProject , cs ,  project);
        dataset.addValue(csPrize , cs , prize );
        dataset.addValue(csPatent , cs , patent);

        dataset.addValue(sePaper, se , paper);
        dataset.addValue( seProject, se, project);
        dataset.addValue( sePrize , se , prize);
        dataset.addValue( sePatent , se, patent);

        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));
        //应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);
//        BarGraph chart = new BarGraph("科研信息", "教研室科研对比");
        String chartTitle="教研室科研对比";
        JFreeChart chart=ChartFactory.createBarChart( chartTitle,
                "教研室",
                "数量",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 800; /* Width of the image */
        int height = 480; /* Height of the image *///存在这个地方 前端用相对路径
        File file = new File( "C:/idea project/school2/school/src/main/resources/static/img/table/barchart.jpg" );//res下
//        if(file.)  重新写入了
        try {
            ChartUtilities.saveChartAsJPEG(file, chart, width , height );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toString();

    }

    public  static String getBarchart2(int csPaper,int csProject,int csPrize,int csPatent,int sePaper,int seProject,int sePrize,int sePatent)  {
        final String cs = "财管";//
        final String se = "信管";
        final String paper = "论文";
        final String project = "项目";
        final String prize = "奖项";
        final String patent = "专利";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        dataset.addValue( csPaper , cs , paper);
        dataset.addValue(csProject , cs ,  project);
        dataset.addValue(csPrize , cs , prize );
        dataset.addValue(csPatent , cs , patent);

        dataset.addValue(sePaper, se , paper);
        dataset.addValue( seProject, se, project);
        dataset.addValue( sePrize , se , prize);
        dataset.addValue( sePatent , se, patent);

        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));
        //应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);
//        BarGraph chart = new BarGraph("科研信息", "教研室科研对比");
        String chartTitle="教研室科研对比";
        JFreeChart chart=ChartFactory.createBarChart( chartTitle,
                "教研室",
                "数量",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 800; /* Width of the image */
        int height = 480; /* Height of the image *///存在这个地方 前端用相对路径
        File file = new File( "C:/idea project/school2/school/src/main/resources/static/img/table/guanlichart.jpg" );//res下
//        if(file.)  重新写入了
        try {
            ChartUtilities.saveChartAsJPEG(file, chart, width , height );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toString();

    }
}
