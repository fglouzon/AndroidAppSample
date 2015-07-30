package net.glouz.myapp.model.models.product;


import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class ProductCategoriesContract
    implements BaseColumns {
  public static final String AUTHORITY = "net.glouz.myapp.model.models.product";

  public static final String CONTENT_URI_PATH = "productcategories";

  public static final String MIMETYPE_TYPE = "productcategories";
  public static final String MIMETYPE_NAME = "net.glouz.myapp.model.models.product.provider";

  public static final int CONTENT_URI_PATTERN_MANY = 3;
  public static final int CONTENT_URI_PATTERN_ONE = 4;

  public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

  private ProductCategoriesContract() {
  }


  public static final String DESCRIPTION = "description";
  public static final String SORTTYPE = "sortType";
}
