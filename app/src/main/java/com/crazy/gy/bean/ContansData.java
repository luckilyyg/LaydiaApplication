package com.crazy.gy.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Administrator
 * 时间：2017/8/5
 * 功能：
 */
public class ContansData {
    static List<CirContents> mList;

    public static List<CirContents> backDatas() {
        mList = new ArrayList<>();
        List<Favort> goodjobs1 = new ArrayList<>();
        goodjobs1.add(new Favort("张三", "10000"));
        goodjobs1.add(new Favort("李四", "144444"));
        goodjobs1.add(new Favort("王老五", "12222"));
        List<Favort> goodjobs2 = new ArrayList<>();
        goodjobs2.add(new Favort("范冰冰", "10000"));
        goodjobs2.add(new Favort("李冰冰", "144444"));
        List<Favort> goodjobs3 = new ArrayList<>();
        goodjobs3.add(new Favort("成龙", "10000"));
        goodjobs3.add(new Favort("陈龙", "144444"));
        List<Favort> goodjobs4 = new ArrayList<>();
        List<Favort> goodjobs5= new ArrayList<>();
        List<Favort> goodjobs6 = new ArrayList<>();
        List<Favort> goodjobs7= new ArrayList<>();
        List<Favort> goodjobs8= new ArrayList<>();
        List<Favort> goodjobs9 = new ArrayList<>();
        List<Favort> goodjobs10 = new ArrayList<>();
        List<Comment> replay1 = new ArrayList<>();
        replay1.add(new Comment("1000","权志龙","1000","彭于晏", "中国的教育有待重视！"));
        replay1.add(new Comment("1000","权志龙","1000","尼坤", "韩国的教育有待重视！"));
        replay1.add(new Comment("1000","赵雅芝","1000","汪涵", "美国的教育有待重视！"));
        replay1.add(new Comment("1000","何炅","1000","沙溢", "德国的教育有待重视！"));
        //(String userId, String userNickname, String appointUserid, String appointUserNickname, String content)
//        replay1.add(new Comment("林青霞", "中国的教育有待重视！"));
//        replay1.add(new Comment("权志龙", "韩国的教育有待改善!"));
        List<Comment> replay2 = new ArrayList<>();
        List<Comment> replay3 = new ArrayList<>();
        List<Comment> replay4 = new ArrayList<>();
        List<Comment> replay5 = new ArrayList<>();
        replay5.add(new Comment("1005","赵薇","","","赵薇的葡萄汁"));
        replay5.add(new Comment("1002","彭于晏", "1003","赵薇","中"));
        replay5.add(new Comment("1006","赵薇","1008","李小龙", "国"));
        replay5.add(new Comment("1006","哪吒","1008","葫芦娃", "的"));
        replay5.add(new Comment("1006","太白金星","1008","后羿", "教"));
        replay5.add(new Comment("1006","吴京","1008","红孩儿", "育"));
        replay5.add(new Comment("1006","孙俪","1008","邓超", "有"));
        replay5.add(new Comment("1006","孙悟空","1008","唐僧", "待"));
        replay5.add(new Comment("1006","唐僧","1008","猪八戒", "改"));
        replay5.add(new Comment("1006","曹操","1008","关羽", "善!"));


        List<Comment> replay6 = new ArrayList<>();
        List<Comment> replay7 = new ArrayList<>();
        List<Comment> replay8 = new ArrayList<>();
        List<Comment> replay9 = new ArrayList<>();
        List<Comment> replay10 = new ArrayList<>();
        mList.add(new CirContents("http://pic.58pic.com/58pic/16/60/18/23E58PICzNY_1024.jpg", "Jack", 10, 12, "本人会坚持在这个项目上实践最新的技术，也会争取拓展更多的阅读内容，欢迎各位关注！ 注意：本项目还在测试阶段，发现 bug 或有好的建议欢迎issue、email(jaydenxiao2016@gmail.com),如果感觉对你有帮助也欢迎点个 star、fork，" +
                "本项目仅做学习交流使用，API 数据内容所有权归原作公司所有，请勿用于其他用途" +
                ",更多精彩文章请关注微信公众号\"Android经验分享\"：这里将长期为您分享Android高手经验、" +
                "中外开源项目、源码解析、框架设计和Android好文推荐！", "2017-8-5", 14, goodjobs1,replay1));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 11, 13, "教你如何买到特价机票，这个可以马住好好研究下！", "2017-8-6", 15,goodjobs2,replay2));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 12, 14, "教你如何买到特价机票，这个可以马住好好研究下！", "2017-8-7", 16, goodjobs3,replay3));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 13, 15, "教你如何买到特价机票，这个可以马住好好研究下-题外话：虽然这里面可以通过xml属性" +
                "进行设置，但它没有提供完善的java代码设置方案，所以如果真的需要可以自定在源码中进行" +
                "添加。---这个开源项目做的好的一点就是最大限度的引用了现有的控件，这里显示文字的仍旧" +
                "是textview，我们可以很方便的进行操作。而外层的ExpandableTextView就是一个容器，对textv" +
                "iew进行修改。！", "2017-8-8", 17, goodjobs4,replay4));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 14, 17, "教你如何买到特价机票，这个可以马住好好研究下！", "2017-8-9", 18,goodjobs5,replay5));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 15, 18, "教你如何买到特价机票，这个可以马住好好研究下！", "2017-8-10", 19,goodjobs6,replay6));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 16, 19, "教你如何买到特价机票，这个可以马住好好研究下！", "2017-8-11", 20,goodjobs7,replay7));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 17, 20, "教你如何买到特价机票，这个可以马住好好研究下！", "2017-8-12", 21,goodjobs8,replay8));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 18, 21, "教你如何买到特价机票，这个可以马住好好研究下！", "2017-8-13", 22,goodjobs9,replay9));
        mList.add(new CirContents("http://img.zcool.cn/community/018de756a3798c6ac7256cb087794d.jpg@900w_1l_2o_100sh.jpg", "Jack", 19, 22, "教你如何买到特价机票，这个可以马住好好研究下！", "2017-8-14", 23,goodjobs10,replay10));
        return mList;
    }
}
