package com.kbms.kb.repository.http;


/**
 *
 * @author Yun Zhenhuan
 * @Description HTTP工具类
 * @Time 2016年5月11日 下午8:57:07
 */
public interface HttpContent {

    /**
     * 基本URL
     */
    // static final String URL = "http://139.199.23.186/";// 基础URL
    static final String URL = "http://www.kbms.io/";// 基础URL
    /**
     * 用户注册时使用的URL，提交方式采用POST提交方式
     */
    static final String regUrl = URL + "user/reg";// 注册URL使用POST请求
    /**
     * 登录接口采用POST提交方式
     */
    //static final String loginUrl = URL + "user/login";// 登录接口使用POST请求
    /**
     * 用户注销提交方式，采用GET提交方式提交
     */
    static final String logoutUrl = URL + "user/logout";// 注销接口GET请求
    /**
     * 查询所有部门，提交方式采用GET提交方式
     */
    static final String departmentUrl = URL + "branch/findAll";// 查找所有部门接口GET请求
    /**
     * 查询二级菜单提交方式，需要在最后拼接一级菜单编号
     */
    static final String SecMenu = URL + "secondMenu/findAll/";// 查找二级菜单请求，GET需要传进来部门编号
    /**
     * 顶部公告请求，采用GET方式提交
     */
    static final String noticeUrl = URL + "topNotice/findAll";// GET请求

    /**
     * 研究服务报名，采用POST请求
     */
    static final String researchEnlist = URL + "research/add";// POST请求
    /**
     * 客户活动报名，采用POST请求
     */
    static final String customFlexible = URL + "client/add";// POST请求
    /**
     * 资金介绍方 POST请求
     */
    static final String introURL = URL + "intro/add";// POST请求

    /**
     * 资金使用方 POST请求
     */
    static final String useURL = URL + "use/add";// POST请求

    /**
     * 修改密码URL POST请求
     */
    static final String changPass = URL + "user/update/password";
    /**
     * 修改邮箱URL POST请求
     */
    static final String changeEmail = URL + "user/update/email";

    /**
     * 修改座机URL PSOT请求
     */
    static final String chagepone = URL + "user/update/phone";

    /**
     * 修改用户手机 POST请求
     */
    static final String changeMoblie = URL + "user/update/mobile";

    /**
     * 修改用户自己姓名 POST请求
     */
    static final String changeName = URL + "/user/update/name";
    /**
     * 修改用户自己头像 POST请求
     */
    static final String changeUICON = URL + "user/update/logo";
    /**
     * 获取用户信息 GET请求
     */
    static final String getUserInfo = URL + "user/ownIndex";

    /**
     * 上传图像
     */
    static final String UploadIcon = URL + "file/upload";
    /**
     * 点赞接口
     */
    static final String zambiaUrl = URL + "support/add";
    /**
     * 取消点赞接口
     */
    static final String nuZambiaURL = URL + "support/del";
    /**
     * 底部公告 GET请求
     */
    static final String bottomNotice = URL + "bottomNotice/findOne";

    /**
     * 知识库列表 GET请求 添加参数
     */
    static final String code = URL + "repository/findAll/";

    /**
     * 案例列表 GET请求
     */
    static final String caseUrl = URL + "case/findAll/";
    /**
     * 讨论区：主题列表 GET请求
     */
    static final String findNote = URL + "/note/findNote/";
    /**
     * 讨论区：所有主题列表 GET请求
     */
    static final String findNoteAll = URL + "/note/findAll";

    /**
     * 讨论区精华帖 GET请求
     */
    static final String findCream = URL + "note/findCream";

    /**
     * 获取主题详情 GET请求，添加参数为noteId 根据主题编号
     */
    static final String findDetailURL = URL + "note/findDetail/";
    /**
     * POST请求 发表评论
     */
    static final String replyURL = URL + "reply/add";

    /**
     * POST请求，删除评论
     */
    static final String delReply = URL + "reply/del";

    /**
     * 一级菜单目录 GET请求
     */
    static final String stairMenuURL = URL + "stairMenu/findAll";

    /**
     * 发表帖子
     */
    static final String addNotre = URL + "note/add";

    /**
     * 帖子中的上传附件
     */
    static final String uploadFile = URL + "file/affix/upload";

    static final String postInfo = URL + "note/findDetail/";

    /**
     *
     * @author Yun Zhenhuan
     * @Description 选择使用GET提交方式还是POST提交方式
     * @Time 2016年5月11日 下午9:31:11
     */
    enum HttpModel {
        POST, GET, FILE;
    }

    static final int TIMEOUT = 3000;

}

