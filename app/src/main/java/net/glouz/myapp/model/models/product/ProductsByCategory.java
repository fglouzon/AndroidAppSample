package net.glouz.myapp.model.models.product;

import android.provider.BaseColumns;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation;

import net.glouz.myapp.model.models.BaseModel;
import net.glouz.myapp.model.models.TableName;

import java.util.Collection;

/**
 * Created by glouzonf on 14/05/2015.
 */
@AdditionalAnnotation.Contract
@DatabaseTable(tableName = TableName.PRODUCTS_BY_CATEGORY)
@JsonIgnoreProperties({ "AlsoSearched", "Facets" })
public class ProductsByCategory extends BaseModel {

    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
    private int id;

    @JsonProperty("Description")
    @DatabaseField
    public String description;

    @JsonProperty("ItemCount")
    @DatabaseField
    public Integer itemCount;

    @JsonProperty("Listings")
    @ForeignCollectionField(eager = true)
    public Collection<Product> listings;

    @JsonProperty("RedirectUrl")
    @DatabaseField
    public String redirectUrl;

    @JsonProperty("SortType")
    @DatabaseField
    public String sortType;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ProductCategory productCategory;

    public ProductsByCategory() {
    }

    public Collection<Product> getListings() {
        return listings;
    }

    public void setListings(Collection<Product> listings) {
        this.listings = listings;
    }
}
