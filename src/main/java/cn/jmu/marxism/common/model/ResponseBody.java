package cn.jmu.marxism.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author PlumK
 * @version 1.0
 * @date 2020/6/17 11:23
 * 通用响应体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBody {
    private String code;
    private String msg;
    private Object data;
}
