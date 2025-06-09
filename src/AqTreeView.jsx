import { createElement, useEffect, useState } from "react";
import Box from "@mui/material/Box";
import { RichTreeView } from "@mui/x-tree-view/RichTreeView";
import { MxTreeItem } from "./components/MxTreeItem";
import { useTreeViewApiRef } from "@mui/x-tree-view";

import "./ui/AqTreeView.css";

export function AqTreeView(props) {
    const { treeData } = props;
    if (treeData.status !== "available" || treeData.items?.length <= 0) {
        return <div></div>;
    }

    const [objectMap, setObjectMap] = useState({});
    const [iconMap, setIconMap] = useState({});
    const apiRef = useTreeViewApiRef();

    const {
        selectedItem,
        rootAssociation,
        childAssociation,
        itemId,
        itemIsDisabled,
        expansionTrigger,
        onItemSelectionChanged,
        labelShowAs,
        labelAttribute,
        labelDynamic,
        itemClasses,
        collapseIconType,
        expandIconType,
        endIconType,
        icons
    } = props;

    useEffect(() => {
        // this feels unnecessary but the only way to get access to the child association is to build a dictionary
        // up front with all items and their association to retrieve later
        // if you try to access the associations later when processing a single object, it reports as unavailable
        console.debug("Updating tree with new tree data.");
        const associations = {};
        treeData.items?.forEach(item => {
            associations[item.id] = childAssociation.get(item);
        });
        setObjectMap(associations);
    }, [treeData]);

    useEffect(() => {        
        setIconMap(
            icons.reduce((result, icon) => ((result[icon.iconType] = icon.icon), result), {})
        )
    }, [icons]);

    const getLabelContent = item => {
        switch (labelShowAs) {
            case "attribute":
                return labelAttribute.get(item).value;
            case "dynamicText":
                return labelDynamic.get(item).value;
        }
    };

    const getTreeData = () => {
        // get our root items
        let rootItems = [];
        if (rootAssociation.type === "Reference") {
            if (rootAssociation.value) {
                rootItems = [rootAssociation.value];
            }
        } else {
            rootItems = rootAssociation.value;
        }
        return rootItems.map(item => getTreeItemForMxObject(item));
    };

    const getChildTreeNodes = mxObject => {
        const childItems = objectMap[mxObject.id]?.value;

        if (childItems && childItems.length > 0) {
            return childItems.map(item => getTreeItemForMxObject(item));
        }

        return;
    };

    const getTreeItemForMxObject = mx => {
        return {
            id: itemId.get(mx).value,
            label: getLabelContent(mx),
            disabled: itemIsDisabled ? itemIsDisabled.get(mx).value : false,
            children: getChildTreeNodes(mx),
            mx,
            itemClasses,
            collapseIconType,
            expandIconType,
            endIconType
        };
    };

    const isItemDisabled = item => {
        return item.disabled ?? false;
    };

    /*
    Label editing requires Pro subscription and following to be enabled: 
        experimentalFeatures={{ labelEditing: true}}
    */
    // const isItemEditable= (item) => {
    //     return labelShowAs === "attribute";
    // }

    // const handleItemLabelChange = (itemId, label) => {
    //     if (itemId != null && apiRef.current) {
    //         const item = apiRef.current.getItem(itemId);
    //         labelAttribute.get(item.mx).setValue(label);
    //     }
    // };

    const handleSelectedItemsChange = (event, itemId) => {
        if (itemId != null && apiRef.current) {
            const item = apiRef.current.getItem(itemId);
            //set the selected item association
            if(selectedItem){
                selectedItem.setValue(item.mx);
            }
            const onItemSelectionChangedAction = onItemSelectionChanged ? onItemSelectionChanged.get(item.mx) : null;
            if (onItemSelectionChangedAction && onItemSelectionChangedAction.canExecute && !onItemSelectionChangedAction.isExecuting) {
                onItemSelectionChangedAction.execute();
            }
        }
    };

    return (
        <Box sx={{ minHeight: 352, minWidth: 250 }}>
            <RichTreeView
                apiRef={apiRef}
                items={getTreeData()}
                isItemDisabled={isItemDisabled}
                expansionTrigger={expansionTrigger}
                onSelectedItemsChange={handleSelectedItemsChange}
                slots={{ item: MxTreeItem }}
                slotProps={{
                    item: { icons: iconMap }
                }} //Allows us to inject custom props into our MxTreeItem2 component, this becomes available in the item as props.icons
            />
        </Box>
    );
}
