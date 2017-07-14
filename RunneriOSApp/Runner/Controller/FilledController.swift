//
//  FilledController.swift
//  Runner
//
//  Created by David Tong on 6/24/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class FilledController: BaseViewController {

    
    @IBOutlet weak var heightOfMessage: NSLayoutConstraint!
    @IBOutlet weak var messageView: UIView!
    @IBOutlet weak var tableView: UITableView!
    
    fileprivate var orderFilledViewModel = OrderFilledViewModel()
    fileprivate var orderFilledViewDataSource : OrderFilledViewDataSource?
    fileprivate var orderFilledViewDelegate : OrderFilledViewDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func initializeView() {
        super.initializeView()
        heightOfMessage.constant = 0
        initializeViewModel()
        initializeNotification()
    }
    
    //MARK: Initialization Model
    
    func initializeViewModel() {
        
        orderFilledViewDataSource = OrderFilledViewDataSource(vModel: orderFilledViewModel)
        orderFilledViewDelegate = OrderFilledViewDelegate()
        tableView.dataSource = orderFilledViewDataSource
        tableView.delegate = orderFilledViewDelegate
        tableView.tableFooterView = UIView()
        //Load data
        orderFilledViewModel.fetchOrderFilled()
        //Clicked OrderDetailController
        orderFilledViewDelegate?.clickedItemAtIndexPath = { [weak self]
            (indexpath) in
            
            guard let weakself = self else { return }
            let orderDetailController = weakself.storyboard?.instantiateViewController(withIdentifier: "OrderDetailController") as! OrderDetailController
            orderDetailController.activityType = 1
            orderDetailController.modalPresentationStyle = .overCurrentContext
            weakself.present(orderDetailController, animated: true, completion: nil)
        }
    }
    
    func initializeNotification() {
        NotificationCenter.default.addObserver(self, selector: #selector(deliverOrderNotification), name: NSNotification.Name(rawValue: "DeliverOrder"), object: nil)
    }
    
    func deliverOrderNotification() {
        
        self.heightOfMessage.constant = 164
        UIView.animate(withDuration: 0.5) {
            self.view.layoutIfNeeded()
            self.perform(#selector(self.dismissMessage), with: nil, afterDelay: 2.0)
        }
    }
    
    func dismissMessage() {
        self.heightOfMessage.constant = 0
        UIView.animate(withDuration: 0.5) {
            self.view.layoutIfNeeded()
        }
    }
    

    
}
