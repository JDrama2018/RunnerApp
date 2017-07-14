//
//  FilledCell.swift
//  Runner
//
//  Created by David Tong on 6/24/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit

class FilledCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

    @IBAction func onDeliverClicked(_ sender: Any) {
        NotificationCenter.default.post(name: NSNotification.Name(rawValue: "DeliverOrder"), object: nil)
    }
}
