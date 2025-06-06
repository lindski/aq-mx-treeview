import { createElement } from "react";
import Box from "@mui/material/Box";
import { RichTreeView } from "@mui/x-tree-view/RichTreeView";

export function preview() {
    return <Box sx={{ minHeight: 352, minWidth: 250 }}>
            <RichTreeView
                items={[{"id":"one", "label": "Item One"}]}
            />
        </Box>;
}

export function getPreviewCss() {
    return require("./ui/AqTreeView.css");
}
