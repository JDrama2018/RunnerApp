//
//  BTExploreBetClubDelegate.swift
//  Betting
//
//  Created by David Tong on 12/23/16.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class OrderFilledViewDelegate : NSObject, UITableViewDelegate {

    var clickedItemAtIndexPath : ((_ indexPath: NSIndexPath) -> Void)?
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        clickedItemAtIndexPath?(indexPath as NSIndexPath)
    }
    
    func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
        
        let deleteAction = UITableViewRowAction(style: .normal, title: "Close") { (rowAction, indexPath) in
            self.clickedItemAtIndexPath?(indexPath as NSIndexPath)
        }
        deleteAction.backgroundColor = UIColor(hex6: 0x009600)
        
        return [deleteAction]
    }
    
}
