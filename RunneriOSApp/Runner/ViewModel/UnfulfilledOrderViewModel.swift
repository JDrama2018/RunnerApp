//
//  UnfulfilledOrderViewModel.swift
//  Runner
//
//  Created by David Tong on 6/22/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class UnfulfilledOrderViewModel : NSObject {

    fileprivate var unfulfilledList = [UnfulfilledOrderModel]()
    var newItemsAdded : ((_ range: NSRange) -> Void)?
    
    var count : Int {
        
        get {
            return unfulfilledList.count
        }
        
    }
    
    func viewModel(at index : Int) -> UnfulfilledOrderModel {
        
        return unfulfilledList[index]
        
    }
    
    func fetchUnfulfilledOrders() {
        for i in 0...5 {
            let model = UnfulfilledOrderModel.mockData(index: i)
            unfulfilledList.append(model)
        }
        self.newItemsAdded?(NSRange(location: 0, length: unfulfilledList.count))
        
    }
}
