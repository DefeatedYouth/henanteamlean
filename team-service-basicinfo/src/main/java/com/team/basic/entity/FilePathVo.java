package com.team.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文件路径表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_file_path")
public class FilePathVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件名称
     */
    @TableField("FILE_NAME")
    private String fileName;

    /**
     * 类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 路径
     */
    @TableField("PATH")
    private String path;

    /**
     * 路径关联ID
     */
    @TableField("PATH_ID")
    private String pathId;

    /**
     * 是否删除
     */
    @TableField("IS_DELETE")
    private Integer isDelete;


}
