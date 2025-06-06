import { createElement, forwardRef } from "react";
import { useTreeItem2Utils } from "@mui/x-tree-view/hooks";
import { useTreeItem2 } from "@mui/x-tree-view/useTreeItem2";
import {
    TreeItem2Checkbox,
    TreeItem2Content,
    TreeItem2IconContainer,
    TreeItem2Label,
    TreeItem2Root,
    TreeItem2GroupTransition
} from "@mui/x-tree-view/TreeItem2";
import { TreeItem2Icon } from "@mui/x-tree-view/TreeItem2Icon";
import { TreeItem2Provider } from "@mui/x-tree-view/TreeItem2Provider";
import { TreeItem2DragAndDropOverlay } from "@mui/x-tree-view/TreeItem2DragAndDropOverlay";
import { TreeItem2LabelInput } from "@mui/x-tree-view/TreeItem2LabelInput";
import { Icon } from "mendix/components/web/Icon";

export const MxTreeItem = forwardRef(function MxTreeItem2(props, ref) {
    const { id, itemId, label, disabled, children } = props;

    const {
        getRootProps,
        getContentProps,
        getIconContainerProps,
        getCheckboxProps,
        getLabelProps,
        getLabelInputProps,
        getGroupTransitionProps,
        getDragAndDropOverlayProps,
        status
    } = useTreeItem2({ id, itemId, children, label, disabled, rootRef: ref });

    const { publicAPI } = useTreeItem2Utils({
        itemId: props.itemId,
        children: props.children
    });

    const item = publicAPI.getItem(props.itemId);
    console.debug("item", item);

    console.log("props", props);
    console.log("getRootProps()", getRootProps());
    console.log("getContentProps()", getContentProps());
    console.log("getIconContainerProps()", getIconContainerProps());
    console.log("getCheckboxProps()", getCheckboxProps());
    console.log("getLabelProps()", getLabelProps());
    console.log("getLabelInputProps()", getLabelInputProps());
    console.log("getGroupTransitionProps()", getGroupTransitionProps());
    console.log("getDragAndDropOverlayProps()", getDragAndDropOverlayProps());
    console.debug("status", status);

    const resolveItemIcon = item => {
        console.debug("resolveItemIcon, item", item);
        const { mx, collapseIconType, expandIconType, endIconType } = item;

        let icon = null;
        if (status.expandable && status.expanded && collapseIconType) {
            const collapseIconTypeValue = collapseIconType.get(mx).value;
            console.debug("resolveItemIcon, collapseIconTypeValue", collapseIconTypeValue);
            icon = props.icons[collapseIconTypeValue];
        } else if (status.expandable && !status.expanded && expandIconType) {
            // show expand icon
            const expandIconTypeValue = expandIconType.get(mx).value;
            console.debug("resolveItemIcon, expandIconTypeValue", expandIconTypeValue);
            icon = props.icons[expandIconTypeValue];
        } else if (!status.expandable && endIconType) {
            // show end icon
            const endIconTypeValue = endIconType.get(mx).value;
            console.debug("resolveItemIcon, endIconTypeValue", endIconTypeValue);
            icon = props.icons[endIconTypeValue];
        }

        console.debug("resolveItemIcon, icon", icon);

        if (icon != null && icon) {
            return <Icon icon={icon.value} />;
        }

        return <TreeItem2Icon status={status} />;
    };

    const getClasses = () => {
      const classes = item.itemClasses.get(item.mx).value;
      console.debug("classes", classes);
      return classes;        
    }

    return (
        <TreeItem2Provider itemId={itemId}>
            <TreeItem2Root {...getRootProps()}>
                <TreeItem2Content
                    {...getContentProps({
                        className: getClasses()
                    })}
                >
                    <TreeItem2IconContainer {...getIconContainerProps()}>
                        {resolveItemIcon(item)}
                    </TreeItem2IconContainer>
                    <TreeItem2Checkbox {...getCheckboxProps()} />
                    {status.editing ? (
                        <TreeItem2LabelInput {...getLabelInputProps()} />
                    ) : (
                        <TreeItem2Label {...getLabelProps()} />
                    )}
                    <TreeItem2DragAndDropOverlay {...getDragAndDropOverlayProps()} />
                </TreeItem2Content>
                {children && <TreeItem2GroupTransition {...getGroupTransitionProps()} />}
            </TreeItem2Root>
        </TreeItem2Provider>
    );
});
