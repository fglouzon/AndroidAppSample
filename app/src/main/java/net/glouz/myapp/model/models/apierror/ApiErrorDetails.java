package net.glouz.myapp.model.models.apierror;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.glouz.myapp.model.models.BaseModel;

/**
 * @author glouzonf
 */
public class ApiErrorDetails extends BaseModel {

	@SerializedName("errors")
	@Expose
	public String errors;

	@SerializedName("ReasonCode")
	@Expose
	public String reasonCode;

	@SerializedName("ReasonMessage")
	@Expose
	public String reasonMessage;

}
