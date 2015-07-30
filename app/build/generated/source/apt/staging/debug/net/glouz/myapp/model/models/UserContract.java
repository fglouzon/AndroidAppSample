package net.glouz.myapp.model.models;


import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class UserContract
    implements BaseColumns {
  public static final String AUTHORITY = "net.glouz.myapp.model.models";

  public static final String CONTENT_URI_PATH = "user";

  public static final String MIMETYPE_TYPE = "user";
  public static final String MIMETYPE_NAME = "net.glouz.myapp.model.models.provider";

  public static final int CONTENT_URI_PATTERN_MANY = 1;
  public static final int CONTENT_URI_PATTERN_ONE = 2;

  public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

  private UserContract() {
  }


  public static final String IDUSER = "idUser";
  public static final String TIMESTAMP = "timestamp";
  public static final String USERNAME = "username";
  public static final String NAME = "name";
  public static final String SURNAME = "surname";
  public static final String TOKEN = "token";
}
