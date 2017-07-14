//
//  BaseViewController.swift
//  Runner
//
//  Created by David Tong on 6/24/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class BaseViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        initializeView()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    //MARK: Initialize view
    
    func initializeView() {
        
    }
    
    //MARK: Helper methods
    
    func showAlert( message : String) {
        
        let alert = UIAlertController(title: "Runner", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { (_) in
            
        }))
        present(alert, animated: true, completion: nil)
        
    }
    
    func showWaitingView() {
        
    }
    

}
