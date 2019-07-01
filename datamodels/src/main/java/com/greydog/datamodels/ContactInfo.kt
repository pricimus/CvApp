package com.greydog.datamodels

data class ContactInfo(
    var name : String? = "",
    var addressLines : List<String>? = null,
    var mobileContact : String? = "",
    var emailAddress : String? = "",
    var linkedin : String? = ""
)