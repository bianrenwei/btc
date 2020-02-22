package com.specimen.btc.web.config.authconfig;

/**
 * @author bianrenwei
 * @date 2020-02-19 20:20 Copyright © SSY All Rights Reserved.
 */
public class RespBean {

  private String ok;

  private String error;

  public String getOk() {
    return ok;
  }

  public void setOk(String ok) {
    this.ok = ok;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public static RespBean ok(String ok) {

    RespBean respBean = new RespBean();
    respBean.setOk(ok);
    return respBean;
  }

  public static RespBean error(String error) {
    RespBean respBean = new RespBean();
    respBean.setError(error);
    return respBean;
  }
}
