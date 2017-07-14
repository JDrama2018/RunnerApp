//
//  BTExploreBetClubDelegate.swift
//  Betting
//
//  Created by David Tong on 12/23/16.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class OrderDetailViewDelegate : NSObject, UITableViewDelegate {

    var clickedItemAtIndexPath : ((_ indexPath: NSIndexPath) -> Void)?
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        clickedItemAtIndexPath?(indexPath as NSIndexPath)
    }
}
