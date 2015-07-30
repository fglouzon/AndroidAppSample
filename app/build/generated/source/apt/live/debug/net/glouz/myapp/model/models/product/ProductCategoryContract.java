package net.glouz.myapp.model.models.product;


import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class ProductCategoryContract
    implements BaseColumns {
  public static final String AUTHORITY = "net.glouz.myapp.model.models.product";

  public static final String CONTENT_URI_PATH = "productcategory";

  public static final String MIMETYPE_TYPE = "productcategory";
  public static final String MIMETYPE_NAME = "net.glouz.myapp.model.models.product.provider";

  public static final int CONTENT_URI_PATTERN_MANY = 5;
  public static final int CONTENT_URI_PATTERN_ONE = 6;

  public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

  private ProductCategoryContract() {
  }


  public static final String CATEGORYID = "categoryId";
  public static final String NAME = "name";
  public static final String PRODUCTCOUNT = "productCount";
  public static final String PRODUCTCATEGORIES = "productCategories";
}
