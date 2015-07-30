package net.glouz.myapp.model.models.product;

import android.provider.BaseColumns;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation;

import net.glouz.myapp.model.models.BaseModel;
import net.glouz.myapp.model.models.TableName;

/**
 * Created by glouzonf on 14/05/2015.
 */
@AdditionalAnnotation.Contract
@DatabaseTable(tableName = TableName.PRODUCT_CATEGORY)
public class ProductCategory extends BaseModel {

    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
    private int id;

    @JsonProperty("CategoryId")
    @DatabaseField
    public String categoryId;

    @JsonProperty("Name")
    @DatabaseField
    public String name;

    @JsonProperty("ProductCount")
    @DatabaseField
    public Integer productCount;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ProductCategories productCategories;

    public ProductCategory() {
    }
}
