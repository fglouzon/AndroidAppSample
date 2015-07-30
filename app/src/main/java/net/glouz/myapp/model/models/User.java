package net.glouz.myapp.model.models;

import android.provider.BaseColumns;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tojc.ormlite.android.annotation.AdditionalAnnotation;

/**
 * Created by glouzonf on 14/05/2015.
 */
@AdditionalAnnotation.Contract
@DatabaseTable(tableName = TableName.USER)
public class User extends BaseModel {

    public User() {
    }

    @DatabaseField(columnName = BaseColumns._ID, generatedId = true)
    public int id;

    @JsonProperty("id_user")
    @DatabaseField
    public String idUser;

    @JsonProperty("timestamp")
    @DatabaseField
    public String timestamp;

    @JsonProperty("username")
    @DatabaseField
    public String username;


    @JsonProperty("name")
    @DatabaseField
    public String name;

    @JsonProperty("surname")
    @DatabaseField
    public String surname;

    @JsonProperty("token")
    @DatabaseField
    public String token;
}
