package com.shouleta.azure.cli.cmd.param;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.shouleta.azure.cli.cmd.CmdCtx;

public abstract class Params {

	private CmdCtx ctx;

	private Map<String, String> params = new HashMap<String, String>();

	/**
	 * Constructor
	 * 
	 * @param ctx
	 */
	public Params(CmdCtx ctx) {
		this.ctx = ctx;
	}

	/**
	 * Get cmd context
	 * 
	 * @return the context
	 */
	public CmdCtx getCtx() {
		return ctx;
	}

	/**
	 * Put k/v into params
	 * 
	 * @param key
	 * @param value
	 */
	private void put(String key, String value) {
		this.params.put(key, value);
	}

	/**
	 * Get a parameter with specified key
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return this.params.get(key);
	}

	protected abstract String[] getParamNames();

	/**
	 * Extract parameters from the properties
	 * 
	 * @param properties
	 */
	public Params init(Map<String, String> properties) {
		String[] paramsName = getParamNames();
		for (String key : paramsName) {
			String value = properties.get(key);
			if (StringUtils.isNotBlank(value)) {
				this.put(key, value);
			}
		}
		return this;
	}

	/**
	 * Transform an object array to a string array
	 * @param objects
	 * @return
	 */
	protected String[] getParamNames(Object[] objects) {
		String[] arr = new String[objects.length];
		for (int i = 0; i < objects.length; i++) {
			arr[i] = objects[i].toString();
		}
		return arr;
	}

}
