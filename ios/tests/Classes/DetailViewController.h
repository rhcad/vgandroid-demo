// DetailViewController.h
// Copyright (c) 2012-2013, https://github.com/rhcad/touchvg

#import <UIKit/UIKit.h>
#import "ARCMacro.h"

@interface DetailViewController : UIViewController <UISplitViewControllerDelegate>

@property (nonatomic, STRONG) UIViewController *content;

- (void)clearCachedData;
- (void)editDetailPage:(id)sender;
- (void)saveDetailPage:(id)sender;

@end
