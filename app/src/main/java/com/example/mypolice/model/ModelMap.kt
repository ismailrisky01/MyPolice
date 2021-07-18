package com.example.mypolice.model

class ModelMap(val html_attributions:Any,val next_page_token:String,val result:ArrayList<Result>,val status:String) {
}
//class Result(val business_status:String, val geometry:Any,val icon:String, val name:String,val opening_hours:Any,val photos:Any,val place_id:String,val plus_code:Any,val rating:Int,val reference:String,val scope:String,val types:Any,val user_ratings_total:Int,val vicinity:String){}

class Location(var lat :Double,var lng :Double) {
}

class Northeast(var lat :Double,var lng :Double) {

}

class Southwest(var lat :Double,var lng :Double) {


}

class Viewport(var northeast: Northeast,var southwest: Southwest) {
}

class Geometry(var location: Location,var viewport: Viewport) {


}

class OpeningHours(var open_now :Boolean) {

}

class Photo(var height:Int,var html_attributions: List<String>,var photo_reference: String,var width :Int) {
}

class PlusCode(var compound_code: String,var global_code: String) {


}

class Result(var business_status: String,var geometry: Geometry,var icon: String,var name: String,var opening_hours: OpeningHours,
             var photos: List<Photo>,var place_id: String,var plus_code: PlusCode,var rating : Double,var reference: String,var scope: String,
             var types: List<String>,var user_ratings_total :Int,var vicinity: String,var permanently_closed :Boolean

) {
}

class ModelRoot(var html_attributions: List<Any>?,var next_page_token: String,var results: List<Result>,var status: String? = null) {

}
data class PostModel(val userId:Int,
                     val id:Int,
                     val title:String,
                     val body:String)