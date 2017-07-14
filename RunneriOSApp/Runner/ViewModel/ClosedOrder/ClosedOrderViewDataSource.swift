//
//  BTExploreBetClubDataSource.swift
//  Betting
//
//  Created by David Tong on 12/23/16.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit
import RxSwift
import RxCocoa

class ClosedOrderViewDataSource : NSObject, UITableViewDataSource {

    var viewModel : ClosedOrderViewModel?
    let disposeBag = DisposeBag()
    
    init( vModel : ClosedOrderViewModel) {
        viewModel = vModel
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        if let source = viewModel {
            return source.count
        }
        return 0
        
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell : UITableViewCell = tableView.dequeueReusableCell(withIdentifier: "ClosedCell", for: indexPath) 
        cell.backgroundColor = UIColor.clear
        return cell
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

}
