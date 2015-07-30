package net.glouz.myapp.model.models.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation;

import net.glouz.myapp.model.models.TableName;

/**
 * Created by glouzonf on 14/05/2015.
 */
@AdditionalAnnotation.Contract()
@DatabaseTable(tableName = TableName.PRODUCT_DETAILS)
@JsonIgnoreProperties({ "Colour", "Size", "AssociatedProducts", "Variants"})
public class ProductDetails extends Product {

//    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
//    private int id;

    @JsonProperty("InStock")
    @DatabaseField
    public Boolean inStock;

    @JsonProperty("PriceType")
    @DatabaseField
    public String priceType;

    @JsonProperty("Sku")
    @DatabaseField
    public String sku;

    @JsonProperty("AdditionalInfo")
    @DatabaseField
    public String additionalInfo;

    @JsonProperty("CareInfo")
    @DatabaseField
    public String careInfo;

    @JsonProperty("Description")
    @DatabaseField
    public String description;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Product product;

    public ProductDetails() {
    }
}
