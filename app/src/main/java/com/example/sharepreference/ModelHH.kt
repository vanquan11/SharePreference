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
    @SerializedName("Description") var description: String = "",
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
    @SerializedName("OrderQuickNotes") var orderQuickNotes: String = "",
    @SerializedName("Position") var position: Int = 0,
    @SerializedName("Price") var price: Long = 0,
    @SerializedName("PriceByBranch") var priceByBranch: Int = 0,
    @SerializedName("PriceByBranchLargeUnit") var priceByBranchLargeUnit: Long = 0,
    @SerializedName("PriceConfig") var priceConfig: String = "",
    @SerializedName("PriceLargeUnit") var priceLargeUnit: Int = 0,
    @SerializedName("Printer") var printer: String = "",
    @SerializedName("ProductAttributes") var productAttributes: List<Any> = listOf(),
    @SerializedName("ProductImages") var productImages: List<ProductImage> = listOf(),
    @SerializedName("ProductType") var productType: Int = 0,
    @SerializedName("RecentPurchasePrice") var recentPurchasePrice: Int = 0,
    @SerializedName("RecentPurchasePriceLargeUnit") var recentPurchasePriceLargeUnit: Int = 0,
    @SerializedName("RetailerId") var retailerId: Int = 0,
    @SerializedName("ShowOnBranchId") var showOnBranchId: List<String> = listOf(),
    @SerializedName("SplitForSalesOrder") var splitForSalesOrder: Boolean = false,
    @SerializedName("TotalOnHand") var totalOnHand: Int = 0,
    @SerializedName("Unit") var unit: String = ""
) : Serializable{
        fun cloneToProductPost() : ProductToPost {
                return ProductToPost(
                        cost = cost,
                        maxQuantity = maxQuantity,
                        minQuantity = minQuantity,
                        onHand = onHand,
                        priceByBranch = priceByBranch,
                        priceByBranchLargeUnit = priceByBranchLargeUnit,
                        productPost = ProductPost(
                                blockOfTimeToUseService = blockOfTimeToUseService,
                                bonusPoint = bonusPoint,
                                bonusPointForAssistant =  bonusPointForAssistant,
                                bonusPointForAssistant2 = bonusPointForAssistant2,
                                bonusPointForAssistant3 = bonusPointForAssistant3,
                                coefficient = coefficient,
                                compositeItems1 = compositeItemProducts,
                                conversionValue = conversionValue,
                                id = id,
                                name = name,
                                price = price,
                                priceConfig = priceConfig,
                                printer = printer,
                                productAttributes = productAttributes,
                                productImages = productImages,
                                productPartners = productAttributes,
                                productType = productType,
                                showOnBranchId = showOnBranchId
                        )
                )
        }
        fun cloneToProductUpdate() : ProductToUpdate{
                return ProductToUpdate(

                )
        }
}

data class Category(
    @SerializedName("CreatedBy") var createdBy: Int = 0,
    @SerializedName("CreatedDate") var createdDate: String = "",
    @SerializedName("Id") var id: Int = 0,
    @SerializedName("ModifiedBy") var modifiedBy: Int = 0,
    @SerializedName("ModifiedDate") var modifiedDate: String = "",
    @SerializedName("Name") var name: String = "",
    @SerializedName("ParentId") var parentId: Int = 0,
    @SerializedName("RetailerId") var retailerId: Int = 0,
    @SerializedName("ShowOnBranchId") var showOnBranchId: String = ""
) :Serializable

data class ProductImage(
    @SerializedName("Id") var id: Int = 0,
    @SerializedName("ImageURL") var imageURL: String = "",
    @SerializedName("IsDefault") var isDefault: Boolean = false,
    @SerializedName("ProductId") var productId: Int = 0,
    @SerializedName("ThumbnailUrl") var thumbnailUrl: String = ""
): Serializable

data class ObResponsPut(
    @SerializedName("AttributesName") var attributesName: String = "",
    @SerializedName("BlockOfTimeToUseService") var blockOfTimeToUseService: Int = 0,
    @SerializedName("BonusPoint") var bonusPoint: Int = 0,
    @SerializedName("BonusPointForAssistant") var bonusPointForAssistant: Int = 0,
    @SerializedName("BonusPointForAssistant2") var bonusPointForAssistant2: Int = 0,
    @SerializedName("BonusPointForAssistant3") var bonusPointForAssistant3: Int = 0,
    @SerializedName("CategoryId") var categoryId: Int = 0, //
    @SerializedName("Code") var code: String = "",
    @SerializedName("Coefficient") var coefficient: Int = 0,
    @SerializedName("ConversionValue") var conversionValue: Int = 0,
    @SerializedName("CreatedBy") var createdBy: Int = 0,
    @SerializedName("CreatedDate") var createdDate: String = "",
    @SerializedName("Id") var id: Int = 0,
    @SerializedName("IsPercentageOfTotalOrder") var isPercentageOfTotalOrder: Boolean = false,
    @SerializedName("IsPriceForBlock") var isPriceForBlock: Boolean = false,
    @SerializedName("IsSerialNumberTracking") var isSerialNumberTracking: Boolean = false,
    @SerializedName("ModifiedBy") var modifiedBy: Int = 0,//
    @SerializedName("ModifiedDate") var modifiedDate: String = "",//
    @SerializedName("Name") var name: String = "",
    @SerializedName("Position") var position: Int = 0,
    @SerializedName("Price") var price: Int = 0,
    @SerializedName("PriceConfig") var priceConfig: String = "",
    @SerializedName("PriceLargeUnit") var priceLargeUnit: Int = 0,
    @SerializedName("Printer") var printer: String = "",
    @SerializedName("ProductType") var productType: Int = 0,
    @SerializedName("RetailerId") var retailerId: Int = 0,
    @SerializedName("ShowOnBranchId") var showOnBranchId: String = "",
    @SerializedName("SplitForSalesOrder") var splitForSalesOrder: Boolean = false,
    @SerializedName("Unit") var unit: String = ""//
)

data class ObResponsPost(
    @SerializedName("AttributesName") var attributesName: String = "",
    @SerializedName("BlockOfTimeToUseService") var blockOfTimeToUseService: Int = 0,
    @SerializedName("BonusPoint") var bonusPoint: Int = 0,
    @SerializedName("BonusPointForAssistant") var bonusPointForAssistant: Int = 0,
    @SerializedName("BonusPointForAssistant2") var bonusPointForAssistant2: Int = 0,
    @SerializedName("BonusPointForAssistant3") var bonusPointForAssistant3: Int = 0,
    @SerializedName("Code") var code: String = "",
    @SerializedName("Coefficient") var coefficient: Int = 0,
    @SerializedName("ConversionValue") var conversionValue: Int = 0,
    @SerializedName("CreatedBy") var createdBy: Int = 0,
    @SerializedName("CreatedDate") var createdDate: String = "",
    @SerializedName("Id") var id: Int = 0,
    @SerializedName("IsPercentageOfTotalOrder") var isPercentageOfTotalOrder: Boolean = false,
    @SerializedName("IsPriceForBlock") var isPriceForBlock: Boolean = false,
    @SerializedName("IsSerialNumberTracking") var isSerialNumberTracking: Boolean = false,
    @SerializedName("Name") var name: String = "",
    @SerializedName("Position") var position: Int = 0,
    @SerializedName("Price") var price: Int = 0,
    @SerializedName("PriceConfig") var priceConfig: String = "",
    @SerializedName("PriceLargeUnit") var priceLargeUnit: Int = 0,
    @SerializedName("Printer") var printer: String = "",
    @SerializedName("ProductType") var productType: Int = 0,
    @SerializedName("RetailerId") var retailerId: Int = 0,
    @SerializedName("ShowOnBranchId") var showOnBranchId: String = "",
    @SerializedName("SplitForSalesOrder") var splitForSalesOrder: Boolean = false
)


data class ProductToPost(
        @SerializedName("CompareCost")
        var compareCost: Int = 0,
        @SerializedName("CompareOnHand")
        var compareOnHand: Int = 0,
        @SerializedName("Cost")
        var cost: Int = 0,
        @SerializedName("MaxQuantity")
        var maxQuantity: Int = 0,
        @SerializedName("MinQuantity")
        var minQuantity: Int = 0,
        @SerializedName("OnHand")
        var onHand: Int = 0,
        @SerializedName("PriceByBranch")
        var priceByBranch: Int = 0,
        @SerializedName("PriceByBranchLargeUnit")
        var priceByBranchLargeUnit: Long = 0,
        @SerializedName("Product")
        var productPost: ProductPost = ProductPost()
) : Serializable

data class ProductPost(
        @SerializedName("BlockOfTimeToUseService")
        var blockOfTimeToUseService: Int = 0,
        @SerializedName("BonusPoint")
        var bonusPoint: Int = 0,
        @SerializedName("BonusPointForAssistant")
        var bonusPointForAssistant: Int = 0,
        @SerializedName("BonusPointForAssistant2")
        var bonusPointForAssistant2: Int = 0,
        @SerializedName("BonusPointForAssistant3")
        var bonusPointForAssistant3: Int = 0,
        @SerializedName("Coefficient")
        var coefficient: Int = 0,
        @SerializedName("CompositeItems1")
        var compositeItems1: List<Any> = listOf(),
        @SerializedName("ConversionValue")
        var conversionValue: Int = 0,
        @SerializedName("ExtraItems1")
        var extraItems1: List<Any> = listOf(),
        @SerializedName("Id")
        var id: Int = 0,
        @SerializedName("Name")
        var name: String = "",
        @SerializedName("Price")
        var price: Long = 0,
        @SerializedName("PriceConfig")
        var priceConfig: String = "{\"Type\":\"percent\",\"Type2\":\"percent\",\"DontPrintLabel\":false,\"OpenTopping\":false}",
        @SerializedName("Printer")
        var printer: String = "KitchenA",
        @SerializedName("ProductAttributes")
        var productAttributes: List<Any> = listOf(),
        @SerializedName("ProductImages")
        var productImages: List<Any> = listOf(),
        @SerializedName("ProductPartners")
        var productPartners: List<Any> = listOf(),
        @SerializedName("ProductType")
        var productType: Int = 0,
        @SerializedName("ShowOnBranchId")
        var showOnBranchId: List<String> = listOf()
):Serializable


data class ProductToUpdate(
        @SerializedName("CompareCost")
        var compareCost: Int = 0,
        @SerializedName("CompareOnHand")
        var compareOnHand: Int = 0,
        @SerializedName("Cost")
        var cost: Int = 0,
        @SerializedName("MaxQuantity")
        var maxQuantity: Int = 0,
        @SerializedName("MinQuantity")
        var minQuantity: Int = 0,
        @SerializedName("OnHand")
        var onHand: Int = 0,
        @SerializedName("PriceByBranch")
        var priceByBranch: Int = 0,
        @SerializedName("PriceByBranchLargeUnit")
        var priceByBranchLargeUnit: Int = 0,
        @SerializedName("Product")
        var product: ProductUpdate = ProductUpdate()
):Serializable

data class ProductUpdate(
        @SerializedName("AttributesName")
        var attributesName: String = "",
        @SerializedName("BarCodes")
        var barCodes: List<Any> = listOf(),////
        @SerializedName("BlockOfTimeToUseService")
        var blockOfTimeToUseService: Int = 0,//
        @SerializedName("BonusPoint")
        var bonusPoint: Int = 0,
        @SerializedName("BonusPointForAssistant")
        var bonusPointForAssistant: Int = 0,
        @SerializedName("BonusPointForAssistant2")
        var bonusPointForAssistant2: Int = 0,
        @SerializedName("BonusPointForAssistant3")
        var bonusPointForAssistant3: Int = 0,
        @SerializedName("Code")
        var code: String = "",//
        @SerializedName("Coefficient")
        var coefficient: Int = 0,
        @SerializedName("CompositeItems")
        var compositeItems: List<Any> = listOf(),//
        @SerializedName("CompositeItems1")
        var compositeItems1: List<Any> = listOf(),
        @SerializedName("ConversionValue")
        var conversionValue: Int = 0,
        @SerializedName("CreatedBy")
        var createdBy: Int = 0,//
        @SerializedName("CreatedDate")
        var createdDate: String = "",//
        @SerializedName("DeliveryOrderDetails")
        var deliveryOrderDetails: List<Any> = listOf(),////
        @SerializedName("ExtraItems1")
        var extraItems1: List<ExtraItems1> = listOf(),////
        @SerializedName("Id")
        var id: Int = 0,
        @SerializedName("InventoryCountDetails")
        var inventoryCountDetails: List<Any> = listOf(),////
        @SerializedName("IsPercentageOfTotalOrder")
        var isPercentageOfTotalOrder: Boolean = false,//
        @SerializedName("IsPriceForBlock")
        var isPriceForBlock: Boolean = false,//
        @SerializedName("IsSerialNumberTracking")
        var isSerialNumberTracking: Boolean = false,//
        @SerializedName("ManufacturingDetails")
        var manufacturingDetails: List<Any> = listOf(),////
        @SerializedName("ManufacturingMaterials")
        var manufacturingMaterials: List<Any> = listOf(),////
        @SerializedName("ModifiedBy")//
        var modifiedBy: Int = 0,//
        @SerializedName("ModifiedDate")
        var modifiedDate: String = "",//
        @SerializedName("Name")
        var name: String = "",
        @SerializedName("NotebookDetails")
        var notebookDetails: List<Any> = listOf(),////
        @SerializedName("OrderDetails")
        var orderDetails: List<Any> = listOf(),////
        @SerializedName("OtherTransactionDetails")
        var otherTransactionDetails: List<Any> = listOf(),////
        @SerializedName("Position")
        var position: Int = 0,//
        @SerializedName("Price")
        var price: Int = 0,
        @SerializedName("PriceBookDetails")
        var priceBookDetails: List<Any> = listOf(),////
        @SerializedName("PriceConfig")
        var priceConfig: String = "",//
        @SerializedName("PriceLargeUnit")
        var priceLargeUnit: Int = 0,//
        @SerializedName("Printer")
        var printer: String = "",
        @SerializedName("ProductAttributes")
        var productAttributes: List<Any> = listOf(),
        @SerializedName("ProductBranches")
        var productBranches: List<Any> = listOf(),////
        @SerializedName("ProductExtras")
        var productExtras: List<Any> = listOf(),////
        @SerializedName("ProductImages")
        var productImages: List<Any> = listOf(),
        @SerializedName("ProductPartners")
        var productPartners: List<Any> = listOf(),
        @SerializedName("ProductSerials")
        var productSerials: List<Any> = listOf(),////
        @SerializedName("ProductType")
        var productType: Int = 0,
        @SerializedName("PurchaseOrderDetails")
        var purchaseOrderDetails: List<Any> = listOf(),////
        @SerializedName("PurchaseOrderReturnDetails")
        var purchaseOrderReturnDetails: List<Any> = listOf(),////
        @SerializedName("RetailerId")
        var retailerId: Int = 0,//
        @SerializedName("ReturnDetails")
        var returnDetails: List<Any> = listOf(),////
        @SerializedName("RoomHistories")
        var roomHistories: List<Any> = listOf(),////
        @SerializedName("Rooms")
        var rooms: List<Any> = listOf(),////
        @SerializedName("ShowOnBranchId")
        var showOnBranchId: String = "[[21883]]",
        @SerializedName("SplitForSalesOrder")
        var splitForSalesOrder: Boolean = false,//
        @SerializedName("TransferDetails")
        var transferDetails: List<Any> = listOf()////
):Serializable

data class ExtraItems1(
        @SerializedName("ExtraId")
        var extraId: Int = 0,
        @SerializedName("Price")
        var price: Int = 0,
        @SerializedName("Quantity")
        var quantity: Int = 0
)