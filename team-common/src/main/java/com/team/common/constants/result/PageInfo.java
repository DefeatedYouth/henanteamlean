package com.team.common.constants.result;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhengjiang
 * @version 1.0.0
 * @date 2022/7/28 17:14
 * @Des
 **/
@Data
public class PageInfo<T> {

    //总记录数
    public int total;
    //结果集
    public List<T> list;

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list,int total) {
        this.total = total;
        this.list = list;
    }

    /**
     * 包装Page对象
     *
     * @param map
     */
    public PageInfo(Map<String,Object> map) {
        if(Objects.nonNull(map)){
            if(Objects.nonNull(map.get("count"))){
                this.total = Integer.valueOf(map.get("count").toString());
            }else{
                this.total = 0;
            }
            if(Objects.nonNull(map.get("list"))){
                this.list = (List<T>)map.get("list");
            }else{
                this.list = Lists.newArrayList();
            }
        }else{
            this.total = 0;
            this.list = Lists.newArrayList();
        }
    }

}
