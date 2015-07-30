package net.glouz.myapp.model.models.apierror;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.glouz.myapp.model.models.BaseModel;

/**
 * 
 * EXAMPLE {code = 405; description = SchemaValidationError; details = { errors
 * = "The field Username must match the regular expression '
 * [A-Za-z0-9\\-\\.\\'\\s]{1,50}'.,The CountryCode field is required., The
 * RegistrationIPAddress field is required."; } ;}.
 * 
 * @author glouzonf
 *
 */
public class ApiError extends BaseModel {

	@SerializedName("code")
	@Expose
	public int code;

	@SerializedName("description")
	@Expose
	public String description;

	@SerializedName("details")
	@Expose
	public ApiErrorDetails apiErrorDetails;

}
