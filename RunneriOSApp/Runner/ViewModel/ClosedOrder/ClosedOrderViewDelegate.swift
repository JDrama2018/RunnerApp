//
//  BTExploreBetClubDelegate.swift
//  Betting
//
//  Created by David Tong on 12/23/16.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class ClosedOrderViewDelegate : NSObject, UITableViewDelegate {

    var clickedItemAtIndexPath : ((_ indexPath: NSIndexPath) -> Void)?
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        clickedItemAtIndexPath?(indexPath as NSIndexPath)
    }
    
    func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
        
        let deleteAction = UITableViewRowAction(style: .normal, title: "Unclose ") { (rowAction, indexPath) in
            self.clickedItemAtIndexPath?(indexPath as NSIndexPath)
        }
        deleteAction.backgroundColor = UIColor(hex6: 0xCA0000)
        
        return [deleteAction]
    }
    
}
