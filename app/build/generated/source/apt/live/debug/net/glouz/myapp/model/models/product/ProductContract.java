package net.glouz.myapp.model.models.product;


import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class ProductContract
    implements BaseColumns {
  public static final String AUTHORITY = "net.glouz.myapp.model.models.product";

  public static final String CONTENT_URI_PATH = "product";

  public static final String MIMETYPE_TYPE = "product";
  public static final String MIMETYPE_NAME = "net.glouz.myapp.model.models.product.provider";

  public static final int CONTENT_URI_PATTERN_MANY = 1;
  public static final int CONTENT_URI_PATTERN_ONE = 2;

  public static final Uri CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).appendPath(CONTENT_URI_PATH).build();

  private ProductContract() {
  }


  public static final String BASEPRICE = "basePrice";
  public static final String BRAND = "brand";
  public static final String CURRENTPRICE = "currentPrice";
  public static final String HASMORECOLOURS = "hasMoreColours";
  public static final String ISINSET = "isInSet";
  public static final String PREVIOUSPRICE = "previousPrice";
  public static final String PRODUCTID = "productId";
  public static final String PRODUCTIMAGEURL = "productImageUrl";
  public static final String PRODUCTIMAGEURLS = "productImageUrls";
  public static final String RRP = "rRP";
  public static final String TITLE = "title";
  public static final String PRODUCTSBYCATEGORY = "productsByCategory";
}
