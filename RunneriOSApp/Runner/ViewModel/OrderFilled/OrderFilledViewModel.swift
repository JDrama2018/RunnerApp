//
//  UnfulfilledOrderViewModel.swift
//  Runner
//
//  Created by David Tong on 6/22/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class OrderFilledViewModel : NSObject {

    fileprivate var orderDetailList = [UnfulfilledOrderModel]()
    var newItemsAdded : ((_ range: NSRange) -> Void)?
    
    var count : Int {
        
        get {
            return orderDetailList.count
        }
        
    }
    
    func viewModel(at index : Int) -> UnfulfilledOrderModel {
        
        return orderDetailList[index]
        
    }
    
    func fetchOrderFilled() {
        for i in 0...5 {
            let model = UnfulfilledOrderModel.mockData(index: i)
            orderDetailList.append(model)
        }
        self.newItemsAdded?(NSRange(location: 0, length: orderDetailList.count))
        
    }
}
