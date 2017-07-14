//
//  OrderDetailController.swift
//  Runner
//
//  Created by David Tong on 6/24/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class OrderDetailController: BaseViewController {

    @IBOutlet weak var fulfilledOrderButton: UIButton!
    @IBOutlet weak var orderFilledView: UIView!
    @IBOutlet weak var heightOrderFilled: NSLayoutConstraint!
    @IBOutlet weak var heightOfSetMessage: NSLayoutConstraint!
    @IBOutlet weak var setMessagesCollectionView: UICollectionView!
    @IBOutlet weak var setMessagesView: UIView!
    @IBOutlet weak var orderNumberLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    fileprivate var orderDetailViewModel = OrderDetailrViewModel()
    fileprivate var orderDetailViewDataSource : OrderDetailViewDataSource?
    fileprivate var orderDetailViewDelegate : OrderDetailViewDelegate?
    var activityType = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        //Load data
        orderDetailViewModel.fetchOrderDetails()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    

    override func initializeView() {
        
        view.isOpaque = false
        view.backgroundColor = .clear
        heightOfSetMessage.constant = 0
        heightOrderFilled.constant = 0
        orderFilledView.isHidden = true
        setMessagesCollectionView.backgroundColor = UIColor.clear
        setMessagesCollectionView.delegate = self
        setMessagesCollectionView.dataSource = self
        if activityType == 1 {
            fulfilledOrderButton.setBackgroundImage(UIImage(named: "ic_button_close"), for: .normal)
            fulfilledOrderButton.setTitle("Close Order", for: .normal)
        } else if activityType == 2 {
            fulfilledOrderButton.setTitle("Unclose Order", for: .normal)
        }
        initializeViewModel()
    }
    
    func initializeViewModel() {
        
        orderDetailViewDataSource = OrderDetailViewDataSource(vModel: orderDetailViewModel)
        orderDetailViewDelegate = OrderDetailViewDelegate()
        tableView.dataSource = orderDetailViewDataSource
        tableView.delegate = orderDetailViewDelegate
        tableView.tableFooterView = UIView()
        self.orderDetailViewModel.newItemsAdded = { [weak self] (range)in
            guard let weakself = self else { return }
            weakself.tableView.reloadData()
        }
        
    }
    
    //MARK: Actions
    
    @IBAction func onCloseClicked(_ sender: Any) {
        
        self.dismiss(animated: true, completion: nil)
        
    }
    
    @IBAction func onFulfilledClicked(_ sender: Any) {
        if activityType == 0 {
            self.orderFilledView.isHidden = false
            UIView.animate(withDuration: 0.5) {
                self.view.layoutIfNeeded()
            }
        } else if activityType == 1 {
            
        } else {
            
        }
        
        
    }
    
    @IBAction func onLeaveMessageClicked(_ sender: Any) {
        
        self.heightOfSetMessage.constant = 300
        UIView.animate(withDuration: 0.5) {
            self.view.layoutIfNeeded()
        }
    }
    
    @IBAction func onBackClicked(_ sender: Any) {
        
        self.orderFilledView.isHidden = true
        UIView.animate(withDuration: 0.5) {
            self.view.layoutIfNeeded()
        }
        
    }
    
    @IBAction func onOrderClicked(_ sender: Any) {
        self.heightOrderFilled.constant = 164
        UIView.animate(withDuration: 0.5) {
            self.view.layoutIfNeeded()
            NotificationCenter.default.post(name: NSNotification.Name(rawValue: "OrderFilled"), object: nil)
        }
    }
}

extension OrderDetailController : UICollectionViewDelegate, UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 4
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if indexPath.row == 3 {
            let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "customizeCell", for: indexPath)
            let label = cell.viewWithTag(2) as! UILabel
            label.layer.masksToBounds = true
            label.layer.cornerRadius = 10
            return cell
        }
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "messageCell", for: indexPath)
        let label = cell.viewWithTag(1) as! UILabel
        label.layer.masksToBounds = true
        label.layer.cornerRadius = 10
        return cell
    }
    
    func collectionView(collectionView : UICollectionView,layout collectionViewLayout:UICollectionViewLayout,sizeForItemAtIndexPath indexPath:NSIndexPath) -> CGSize {
        let cellSize:CGSize = CGSize(width: 100, height: 40)
        return cellSize
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.heightOfSetMessage.constant = 0
        UIView.animate(withDuration: 0.5) {
            self.view.layoutIfNeeded()
        }
    }
    
    
    
}
