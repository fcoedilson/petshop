package br.com.sample.bean;

import br.com.sample.util.BeanUtil;

public abstract class BaseStateBean extends BaseBean {

	private String currentState;

	public boolean isOpened() {
		Object tmp = BeanUtil.getSession().getAttribute("isOpen");
		if (tmp == null) {
			closePopup();
			return false;
		} else {
			return (Boolean) tmp;
		}
	}

	public boolean isOpenSession(){
		Object o = BeanUtil.getSession().getAttribute("sessionOpened");
		if(o == null){
			return false;
		} else {
			return (Boolean)o;
		}
	}

	public String openPopup() {
		BeanUtil.getSession().setAttribute("isOpen", Boolean.TRUE);
		return SUCCESS;
	}


	public String closePopup() {
		BeanUtil.getSession().setAttribute("isOpen", Boolean.FALSE);
		return SUCCESS;
	}
	
	public boolean isUserIn(){
		Object tmp = BeanUtil.getSession().getAttribute("userIn");
		if (tmp == null) {
			return false;
		} else {
			return (Boolean) tmp;
		}
	}

	protected String currentBeanName() {
		return this.getClass().getSimpleName();
	}

	protected void setCurrentBean(String bean) {
		BeanUtil.getSession().setAttribute("currentBean", bean);
	}

	protected void setCurrentState(String state) {
		this.currentState = state;
	}

	public String getCurrentBean() {
		return (String) BeanUtil.getSession().getAttribute("currentBean");
	}

	public String getCurrentState() {
		return this.currentState == null ? "" : this.currentState;
	}
}