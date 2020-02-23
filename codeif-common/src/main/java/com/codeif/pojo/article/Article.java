package com.codeif.pojo.article;

import com.codeif.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 文章板块: 文章类
 **/
@Getter
@Setter
@ToString
@ApiModel(value = "article", description = "文章类")
@Entity
@Table(name = "ar_article")
public class Article extends BasePojo implements Serializable {


    @ApiModelProperty(value = "文章分类")
    @Transient
    private Category category;

    @ApiModelProperty(value = "推荐阅读",example = "1")
    @Transient
    private String related;

    @ApiModelProperty("用户名")
    @Transient
    private String userName;


    @ApiModelProperty("ID")
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.codeif.config.IdGeneratorConfig")
    private String id;

    @ApiModelProperty("分类ID")
    private String categoryId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("标签")
    @NotNull(message = "标签不能为空")
    private String label;

    @ApiModelProperty("标题")
    @NotNull(message = "标题不能为空")
    private String title;

    @ApiModelProperty("文章封面")
    private String image;

    @ApiModelProperty(value = "是否公开",example = "1")
    private Integer isPublic;

    @ApiModelProperty(value = "是否置顶",example = "1")
    private Integer isTop;

    @ApiModelProperty(value = "浏览量",example = "1")
    private Integer visits;

    @ApiModelProperty(value = "点赞数",example = "1")
    private Integer upvote;

    @ApiModelProperty("评论数")
    private Integer comment;

    @ApiModelProperty(value = "审核状态",example = "1")
    private Integer reviewState;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("热度")
    @Column(precision = 2, scale = 1)
    private float importance;

    @ApiModelProperty("文章描述（概述）")
    @NotNull(message = "概述不能为空")
    private String description;

    @ApiModelProperty("关键字")
    private String keywords;

    @ApiModelProperty("来源（1：原创，2：转载，3：混撰）")
    @NotNull(message = "来源不能为空")
    private Integer origin;

    @ApiModelProperty("文章正文")
    @NotNull(message = "内容不能为空")
    @Lob
    @Column(columnDefinition = "text")
    private String content;

}