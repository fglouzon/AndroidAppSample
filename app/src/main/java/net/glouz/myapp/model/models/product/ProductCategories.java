package net.glouz.myapp.model.models.product;

import android.provider.BaseColumns;

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
@DatabaseTable(tableName = TableName.PRODUCT_CATEGORIES)
public class ProductCategories extends BaseModel {

    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
    private int id;

    @JsonProperty("Description")
    @DatabaseField
    public String description;

    @JsonProperty("Listing")
    @ForeignCollectionField(eager = true)
    public Collection<ProductCategory> list;

    @JsonProperty("SortType")
    @DatabaseField
    public String sortType;

    public ProductCategories() {
    }

    public Collection<ProductCategory> getList() {
        return list;
    }

    public void setList(Collection<ProductCategory> list) {
        this.list = list;
    }
}
