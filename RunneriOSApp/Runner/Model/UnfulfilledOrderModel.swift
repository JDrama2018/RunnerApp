//
//  UnfulfilledOrderModel.swift
//  Runner
//
//  Created by David Tong on 6/22/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit
import ObjectMapper

class UnfulfilledOrderModel : NSObject, Mappable {

    var orderNumber : String?
    var personOrder : String?
    var orderDate : Date?
    
    static func mockData(index : Int) -> UnfulfilledOrderModel {
        
        let object = UnfulfilledOrderModel()
        object.orderNumber = "#43756\(index)"
        object.personOrder = "Susan R."
        object.orderDate = Date()
        return object
    }
    
    required init?(map: Map) {
        orderNumber  <- map["orderNumber"]
        personOrder  <- map["personOrder"]
        orderDate  <- map["orderDate"]

    }
    
    override init() {
        super.init()
    }
    
    func mapping(map: Map) {
        
        orderNumber  <- map["orderNumber"]
        personOrder  <- map["personOrder"]
        orderDate  <- map["orderDate"]
    }
}
