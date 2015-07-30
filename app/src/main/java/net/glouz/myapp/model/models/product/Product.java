package net.glouz.myapp.model.models.product;

import android.provider.BaseColumns;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation;

import net.glouz.myapp.model.models.BaseModel;
import net.glouz.myapp.model.models.TableName;

import java.util.ArrayList;

/**
 * Created by glouzonf on 14/05/2015.
 */
@AdditionalAnnotation.Contract
@DatabaseTable(tableName = TableName.PRODUCT)
@JsonIgnoreProperties({ "AdditionalInfo", "CareInfo", "Description", "Variants", "AssociatedProducts", "Sku", "Size", "PriceType", "InStock", "Colour"})
public class Product extends BaseModel {

    public Product() {
    }

    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
    public int id;

    @JsonProperty("BasePrice")
    @DatabaseField
    public Float basePrice;

    @JsonProperty("Brand")
    @DatabaseField
    public String brand;

    @JsonProperty("CurrentPrice")
    @DatabaseField
    public String currentPrice;

    @JsonProperty("HasMoreColours")
    @DatabaseField
    public Boolean hasMoreColours;

    @JsonProperty("IsInSet")
    @DatabaseField
    public Boolean isInSet;

    @JsonProperty("PreviousPrice")
    @DatabaseField
    public String previousPrice;

    @JsonProperty("ProductId")
    @DatabaseField
    public int productId;

    @JsonProperty("ProductImageUrl")
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<String> productImageUrl;

    @JsonProperty("ProductImageUrls")
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<String> productImageUrls;

    @JsonProperty("RRP")
    @DatabaseField
    public String rRP;

    @JsonProperty("Title")
    @DatabaseField
    public String title;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ProductsByCategory productsByCategory;

    public boolean wishlist;

    public static class ProductList extends ArrayList<Product> {
    }

}
