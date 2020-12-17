package com.example.sharepreference
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ModelHH(
    @SerializedName("__count") var count: Int = 0,
    @SerializedName("results") var results: List<Result> = listOf(),
    @SerializedName("Timestamp") var timestamp: String = ""
) : Serializable

data class Result(
    @SerializedName("AttributesName") var attributesName: String = "",
    @SerializedName("BlockOfTimeToUseService") var blockOfTimeToUseService: Int = 0,
    @SerializedName("BonusPoint") var bonusPoint: Int = 0,
    @SerializedName("BonusPointForAssistant") var bonusPointForAssistant: Int = 0,
    @SerializedName("BonusPointForAssistant2") var bonusPointForAssistant2: Int = 0,
    @SerializedName("BonusPointForAssistant3") var bonusPointForAssistant3: Int = 0,
    @SerializedName("Category") var category: Category = Category(),
    @SerializedName("CategoryId") var categoryId: Int = 0,
    @SerializedName("Code") var code: String = "",
    @SerializedName("Code2") var code2: String = "",
    @SerializedName("Coefficient") var coefficient: Int = 0,
    @SerializedName("CompareMaxQuantity") var compareMaxQuantity: Int = 0,
    @SerializedName("CompareMinQuantity") var compareMinQuantity: Int = 0,
    @SerializedName("CompositeItemProducts") var compositeItemProducts: List<Any> = listOf(),
    @SerializedName("ConversionValue") var conversionValue: Int = 0,
    @SerializedName("Cost") var cost: Int = 0,
    @SerializedName("CreatedBy") var createdBy: Int = 0,
    @SerializedName("CreatedDate") var createdDate: String = "",
    @SerializedName("Formular") var formular: String = "",
    @SerializedName("Hidden") var hidden: Boolean = false,
    @SerializedName("Id") var id: Int = 0,
    @SerializedName("IsPercentageOfTotalOrder") var isPercentageOfTotalOrder: Boolean = false,
    @SerializedName("IsPriceForBlock") var isPriceForBlock: Boolean = false,
    @SerializedName("IsSerialNumberTracking") var isSerialNumberTracking: Boolean = false,
    @SerializedName("IsTimer") var isTimer: Boolean = false,
    @SerializedName("LargeUnit") var largeUnit: String = "",
    @SerializedName("LargeUnitCode") var largeUnitCode: String = "",
    @SerializedName("MaxQuantity") var maxQuantity: Int = 0,
    @SerializedName("MinQuantity") var minQuantity: Int = 0,
    @SerializedName("ModifiedDate") var modifiedDate: String = "",
    @SerializedName("Name") var name: String = "",
    @SerializedName("OnHand") var onHand: Int = 0,
    @SerializedName("OnOrder") var onOrder: Int = 0,
    @SerializedName("Position") var position: Int = 0,
    @SerializedName("Price") var price: Int = 0,
    @SerializedName("PriceByBranch") var priceByBranch: Int = 0,
    @SerializedName("PriceByBranchLargeUnit") var priceByBranchLargeUnit: Int = 0,
    @SerializedName("PriceConfig") var priceConfig: String = "",
    @SerializedName("PriceLargeUnit") var priceLargeUnit: Int = 0,
    @SerializedName("Printer") var printer: String = "",
    @SerializedName("ProductAttributes") var productAttributes: List<Any> = listOf(),
    @SerializedName("ProductImages") var productImages: List<ProductImage> = listOf(),
    @SerializedName("ProductType") var productType: Int = 0,
    @SerializedName("RecentPurchasePrice") var recentPurchasePrice: Int = 0,
    @SerializedName("RecentPurchasePriceLargeUnit") var recentPurchasePriceLargeUnit: Int = 0,
    @SerializedName("RetailerId") var retailerId: Int = 0,
    @SerializedName("ShowOnBranchId") var showOnBranchId: String = "",
    @SerializedName("SplitForSalesOrder") var splitForSalesOrder: Boolean = false,
    @SerializedName("TotalOnHand") var totalOnHand: Int = 0,
    @SerializedName("Unit") var unit: String = ""
) : Serializable

data class Category(
    @SerializedName("CreatedBy") var createdBy: Int = 0,
    @SerializedName("CreatedDate") var createdDate: String = "",
    @SerializedName("Id") var id: Int = 0,
    @SerializedName("Name") var name: String = "",
    @SerializedName("RetailerId") var retailerId: Int = 0,
    @SerializedName("ShowOnBranchId") var showOnBranchId: String = ""
)

data class ProductImage(
    @SerializedName("Id") var id: Int = 0,
    @SerializedName("ImageURL") var imageURL: String = "",
    @SerializedName("IsDefault") var isDefault: Boolean = false,
    @SerializedName("ProductId") var productId: Int = 0,
    @SerializedName("ThumbnailUrl") var thumbnailUrl: String = ""
)