//
//  UnfulfilledController.swift
//  Runner
//
//  Created by David Tong on 6/24/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class UnfulfilledController: BaseViewController {

    @IBOutlet weak var tableView: UITableView!
    
    fileprivate var unfullfilledViewModel = UnfulfilledOrderViewModel()
    fileprivate var unfulfilledViewDataSource : UnfulfilledOrderViewDataSource?
    fileprivate var unfulfilledViewDelegate : UnfulfilledOrderViewDelegate?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func initializeView() {
        super.initializeView()
        initializeViewModel()
        initializeNotification()
    }
    
    //MARK: Initialization Model
    
    func initializeViewModel() {
        
        unfulfilledViewDataSource = UnfulfilledOrderViewDataSource(vModel: unfullfilledViewModel)
        unfulfilledViewDelegate = UnfulfilledOrderViewDelegate()
        tableView.dataSource = unfulfilledViewDataSource
        tableView.delegate = unfulfilledViewDelegate
        tableView.tableFooterView = UIView()
        //Load data
        unfullfilledViewModel.fetchUnfulfilledOrders()
        //Clicked OrderDetailController
        unfulfilledViewDelegate?.clickedItemAtIndexPath = { [weak self]
            (indexpath) in
            
            guard let weakself = self else { return }
            let orderDetailController = weakself.storyboard?.instantiateViewController(withIdentifier: "OrderDetailController") as! OrderDetailController
            orderDetailController.modalPresentationStyle = .overCurrentContext
            weakself.present(orderDetailController, animated: true, completion: nil)
        }
    }
    
    func initializeNotification() {
        NotificationCenter.default.addObserver(self, selector: #selector(orderFilledNotification), name: NSNotification.Name(rawValue: "OrderFilled"), object: nil)
    }
    
    func orderFilledNotification() {
        self.perform(#selector(performNotificationDelay), with: nil, afterDelay: 2.0)
    }
    
    func performNotificationDelay() {
        dismiss(animated: true, completion: nil)
        NotificationCenter.default.post(name: NSNotification.Name(rawValue: "MoveOrderFilled"), object: nil)
    }

}
