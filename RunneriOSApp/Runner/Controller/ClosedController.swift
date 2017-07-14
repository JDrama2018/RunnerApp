//
//  ClosedController.swift
//  Runner
//
//  Created by David Tong on 6/24/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class ClosedController: BaseViewController {

    @IBOutlet weak var tableView: UITableView!
    
    fileprivate var closedOrderViewModel = ClosedOrderViewModel()
    fileprivate var closedOrderViewDataSource : ClosedOrderViewDataSource?
    fileprivate var closedOrderViewDelegate : ClosedOrderViewDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    override func initializeView() {
        super.initializeView()
        initializeViewModel()
    }
    
    //MARK: Initialization Model
    
    func initializeViewModel() {
        
        closedOrderViewDataSource = ClosedOrderViewDataSource(vModel: closedOrderViewModel)
        closedOrderViewDelegate = ClosedOrderViewDelegate()
        tableView.dataSource = closedOrderViewDataSource
        tableView.delegate = closedOrderViewDelegate
        tableView.tableFooterView = UIView()
        //Load data
        closedOrderViewModel.fetchOrderFilled()
        //Clicked OrderDetailController
        closedOrderViewDelegate?.clickedItemAtIndexPath = { [weak self]
            (indexpath) in
            
            guard let weakself = self else { return }
            let orderDetailController = weakself.storyboard?.instantiateViewController(withIdentifier: "OrderDetailController") as! OrderDetailController
            orderDetailController.activityType = 2
            orderDetailController.modalPresentationStyle = .overCurrentContext
            weakself.present(orderDetailController, animated: true, completion: nil)
        }
    }
    
}
