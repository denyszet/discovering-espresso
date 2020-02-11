# **1. Inspecting application UI with Layout Inspector tool:**

To open the Layout Inspector, do the following:

* Run your app on a connected device or emulator.
* Click **Tools > Layout Inspector**.
* In the **Choose Process** dialog that appears, select the app process you want to inspect and click **OK**.

<img src="../../../../../../../assets/locating_ui_elements/layout-inspector-choose_2x.png" alt="Choose Process dialog" width="400"/>

**Figure 1.** The Choose Process dialog

By default, the **Choose Process** dialog lists only the processes for the project currently open in Android Studio and running on the device or emulator. If you want to inspect a different app that's on the device, check **Show all processes**. If you're using a rooted device or an emulator without Google Play store, then you'll see all running apps. Otherwise, you'll see only running apps that are debuggable.

The Layout Inspector captures a snapshot, saves it as a `.li` file, and opens it.

As shown in figure 2, the Layout Inspector displays the following:
1. **View Tree:** The hierarchy of views in the layout.
2. **Layout Inspector toolbar:** Tools for the Layout Inspector.
3. **Screenshot:** Screenshot of app layout as it appears on your device, with layout bounds shown for each view.
4. **Properties Table:** The layout properties for the selected view.

<img src="../../../../../../../assets/locating_ui_elements/layout-inspector-callouts_2x.png" alt="The Layout Inspector" width="800"/>

**Figure 2.** The Layout Inspector

## Select a view
To select a view, click it in the **View Tree** or the screenshot. All of the layout attributes for the selected view appear in the **Properties Table**.
If your layout includes overlapping views, then only the view in front is clickable in the screenshot. To select a view that is not in front, click it in the **View Tree**.

## Isolate a view
To work with complex layouts, you can isolate individual views so that only a subset of the layout is shown in the **View Tree** and rendered in the screenshot.
You can isolate a view only when the device is still connected. Isolating a view requires the device to render the layout so that the Layout Inspector can take another screenshot.

To isolate a view, do one of the following:
* Double-click the view in the screenshot.
* Right-click the view in the **View Tree** and select **Render Subtree Preview**.
  
To return to the containing view, click the arrow in the top-left corner of the **Tree View**.

## Hide layout bounds
To hide the bounding box for a layout element, right-click the element in the **View Tree** and deselect **Show layout bounds**.
A layout element with **Show layout bounds** deselected can't be selected by clicking in the screenshot.

## Zoom in and use a reference grid to inspect layout details
You can control the grid overlay and zoom level of the screenshot using buttons in the Layout Editor toolbar:

* To zoom in on the screenshot, click **Zoom In** <img src="../../../../../../../assets/locating_ui_elements/layout-inspector-zoom-in.png" width="15"/>.
* To zoom out on the screenshot, click **Zoom Out** <img src="../../../../../../../assets/locating_ui_elements/layout-inspector-zoom-out.png" width="15"/>.
* To view the layout at a magnification at which one pixel in the screenshot corresponds to one pixel on the device, click **Actual Size** <img src="../../../../../../../assets/locating_ui_elements/layout-inspector-actual-size.png" width="15"/>.
* To overlay a pixel grid, click **Grid** <img src="../../../../../../../assets/locating_ui_elements/layout-inspector-grid.png" width="15"/>. The grid is available only at high zoom levels.

## Compare app layout to a reference image overlay
To compare your app layout with a reference image, such as a UI mockup, you can load a bitmap image overlay in the Layout Inspector.

* To load an overlay, click **Load Overlay**  at the top of the Layout Inspector. The overlay is scaled to fit the layout.
* To adjust the transparency of the overlay, use the **Alpha** slider.
* To remove the overlay, click **Clear Overlay** .
Take a new screenshot to capture layout changes
If the layout on the device changes, the **Layout Inspector** does not automatically update. To capture layout changes, create a new screenshot by again clicking **Tools > Layout Inspector**.

Each screenshot is saved in a separate `.li` file in `project-name/captures/` and opens in a new tab.

<img src="../../../../../../../assets/locating_ui_elements/layout-inspector-captures_2x.png" alt=".li files" width="400"/>

**Figure 3.** Layout Inspector screenshots (`.li` files) in the Project window

You can reload a previous screenshot by double-clicking the `.li` file in `project-name/captures/`.

*Portions of this page are reproduced from work created and shared by the Android Open Source Project and used according to terms described in the Creative Commons 2.5 Attribution License. (https://developer.android.com/studio/debug/layout-inspector)*

# ****2. Inspecting application UI with Android Device Monitor tool:****

Android Device Monitor was deprecated in Android Studio 3.1 and removed from Android Studio 3.2. But it still remains a powerful tool to inspect applications layout. The main advantage of Android Device Monitor compared to Layout Inspector is the possibility to inspect any application shown to the user at the given moment of time including system and 3rd party applications.

> ***Note:*** *there may be issues with running Android Device Monitor as it is deprecated. You may need to solve them on your own.*

Android Device Monitor is still the part of the Android SDK tools bundle and is located under the `.../Android/sdk/tools` directory. To open Android Device Monitor simply launch it from terminal or command line using `monitor` command.

As shown in figure 4, the Android Device Monitor contains multiple functional areas:
1. **Devices control panel:** with Screen capture and Dump view hierarchy buttons.
2. **Devices list:** list of devices and processes to inspect.
3. **Visual view dumps representation:** view dump screenshots in `.uix` format.
4. **View hierarchy:** the hierarchy which allows you to highlight any view in view dump screenshot.
5. **Node Detail:** list of selected node properties.

<img src="../../../../../../../assets/locating_ui_elements/monitor_areas_split.jpg" alt="Android Device Monitor tool" width="800"/>

**Figure 4.** Android Device Monitor tool

## Dump view hierarchy
To dump view hierarchy that is currently displayed on the mobile device simply select the device in **Devices list** and click on **Dump view hierarchy** button inside **Devices control panel** (figure 5). After that the `.uix` dump file will appear in **Visual view dumps representation** area.

<img src="../../../../../../../assets/locating_ui_elements/monitor_dump_view.jpg" alt="Dump view hierarchy" width="300"/>

**Figure 5.** Dump view hierarchy

## Inspect the view dump layout
To inspect view dump hover any element on the dump screenshot and click on it. The **View hierarchy** will highligt the selected view and its properties will be shown in **Node Detail** view.

In majority of cases the following node (i.e. view) properties are used to locate it inside the layout:
* ***index:*** the position of the node inside its parent.  
* ***text:*** the text of the node if applicable.
* ***resource-id:*** the id of the node represented in the following form `<application_package>:id/<node_id>`.
* ***package:*** application package.
* ***class:*** the class of the node element.
* ***content-desc:*** the content description of the element if applicable.



