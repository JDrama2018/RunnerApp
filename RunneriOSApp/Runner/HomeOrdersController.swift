//
//  UnfulfilledOrdersController.swift
//  Runner
//
//  Created by David Tong on 6/22/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class HomeOrdersController: BaseViewController {

    fileprivate var pageMenu : CAPSPageMenu?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func initializeView() {
        self.automaticallyAdjustsScrollViewInsets = false
        // Array to keep track of controllers in page menu
        var controllerArray : [UIViewController] = []
        let  unfulfilledOrdersController = self.storyboard?.instantiateViewController(withIdentifier: "UnfulfilledOrdersController")
        unfulfilledOrdersController?.title = "Unfulfilled(7)"
        let  filledController = self.storyboard?.instantiateViewController(withIdentifier: "FilledController")
        filledController?.title = "Filled(7)"
        let  closedController = self.storyboard?.instantiateViewController(withIdentifier: "ClosedController")
        closedController?.title = "Closed(7)"
        controllerArray.append(unfulfilledOrdersController!)
        controllerArray.append(filledController!)
        controllerArray.append(closedController!)
        
        let parameters: [CAPSPageMenuOption] = [
            .menuItemSeparatorWidth(0.0),
            .scrollMenuBackgroundColor(UIColor(hex6: 0x282828)),
            .viewBackgroundColor(UIColor(hex6:0x101010)),
            .selectionIndicatorColor(UIColor.white),
            .menuMargin(0.0),
            .menuHeight(40.0),
            .useMenuLikeSegmentedControl(controllerArray.count <= 3),
            .menuItemSeparatorRoundEdges(false),
            .selectionIndicatorHeight(2.5),
            .menuItemWidthBasedOnTitleTextWidth(false),
            .selectedMenuItemLabelColor(UIColor.white),
            .unselectedMenuItemLabelColor(UIColor.white),
            .menuPosition(.top)
        ]
        
        // Initialize page menu with controller array, frame, and optional parameters
        pageMenu = CAPSPageMenu(viewControllers: controllerArray, frame: CGRect(x: 0, y: 64, width: Int(self.view.bounds.size.width), height: Int(self.view.bounds.size.height)), pageMenuOptions: parameters)
        
        // Lastly add page menu as subview of base view controller view
        // or use pageMenu controller in you view hierachy as desired
        self.view.addSubview(pageMenu!.view)
        initializeNotification()
    }
    
    func initializeNotification() {
        NotificationCenter.default.addObserver(self, selector: #selector(orderFilledNotification), name: NSNotification.Name(rawValue: "MoveOrderFilled"), object: nil)
    }
    
    func orderFilledNotification() {
        //Order Filled
        pageMenu?.moveToPage(1)
        
    }
    

}
