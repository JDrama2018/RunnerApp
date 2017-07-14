//
//  ViewController.swift
//  Runner
//
//  Created by David Tong on 6/22/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class LoginViewController: BaseViewController {

    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func initializeView() {
        loginButton.layer.masksToBounds = true
        loginButton.layer.cornerRadius = 6
        usernameTextField.layer.masksToBounds = true
        usernameTextField.layer.cornerRadius = 6
        passwordTextField.layer.masksToBounds = true
        passwordTextField.layer.cornerRadius = 6
    }

    //MARK: Actions
    
    @IBAction func onLoginClicked(_ sender: Any) {
        
        self.performSegue(withIdentifier: "UnfulfilledOrderSegue", sender: nil)
        
    }
   
    
}

