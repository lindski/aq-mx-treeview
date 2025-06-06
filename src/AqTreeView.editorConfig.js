import { hidePropertyIn } from "@mendix/pluggable-widgets-tools";

/**
 * @typedef Property
 * @type {object}
 * @property {string} key
 * @property {string} caption
 * @property {string} description
 * @property {string[]} objectHeaders
 * @property {ObjectProperties[]} objects
 * @property {Properties[]} properties
 */

/**
 * @typedef ObjectProperties
 * @type {object}
 * @property {PropertyGroup[]} properties
 * @property {string[]} captions
 */

/**
 * @typedef PropertyGroup
 * @type {object}
 * @property {string} caption
 * @property {PropertyGroup[]} propertyGroups
 * @property {Property[]} properties
 */

/**
 * @typedef Properties
 * @type {PropertyGroup}
 */

/**
 * @typedef Problem
 * @type {object}
 * @property {string} property
 * @property {("error" | "warning" | "deprecation")} severity
 * @property {string} message
 * @property {string} studioMessage
 * @property {string} url
 * @property {string} studioUrl
 */

/**
 * @param {object} values
 * @param {Properties} defaultProperties
 * @param {("web"|"desktop")} target
 * @returns {Properties}
 */
export function getProperties(values, defaultProperties, target) {
    // Label property display
    if (values.labelShowAs !== "attribute") {
        hidePropertyIn(defaultProperties, values, "labelAttribute");
    }
    if (values.labelShowAs !== "dynamicText") {
        hidePropertyIn(defaultProperties, values, "labelDynamic");
    }
    return defaultProperties;
}

/**
 * @param {Object} values
 * @returns {Problem[]} returns a list of problems.
 */
export function check(values) {
    /** @type {Problem[]} */
    const errors = [];

    switch (values.labelShowAs) {
        case "attribute":
            if (!values.labelAttribute || values.labelAttribute === "") {
                errors.push({
                    property: "labelAttribute",
                    severity: "error",
                    message: "Please select an attribute for the label"
                });
            }
            break;
        case "dynamicText":
            if (!values.labelDynamic || values.labelDynamic === "") {
                errors.push({
                    property: "labelDynamic",
                    severity: "error",
                    message: "Please enter some dynamic text for the label"
                });
            }
            break;
    }
    return errors;
}

const alphaHexByPercent = new Map([
    [100, "FF"],
    [99, "FC"],
    [98, "FA"],
    [97, "F7"],
    [96, "F5"],
    [95, "F2"],
    [94, "F0"],
    [93, "ED"],
    [92, "EB"],
    [91, "E8"],
    [90, "E6"],
    [89, "E3"],
    [88, "E0"],
    [87, "DE"],
    [86, "DB"],
    [85, "D9"],
    [84, "D6"],
    [83, "D4"],
    [82, "D1"],
    [81, "CF"],
    [80, "CC"],
    [79, "C9"],
    [78, "C7"],
    [77, "C4"],
    [76, "C2"],
    [75, "BF"],
    [74, "BD"],
    [73, "BA"],
    [72, "B8"],
    [71, "B5"],
    [70, "B3"],
    [69, "B0"],
    [68, "AD"],
    [67, "AB"],
    [66, "A8"],
    [65, "A6"],
    [64, "A3"],
    [63, "A1"],
    [62, "9E"],
    [61, "9C"],
    [60, "99"],
    [59, "96"],
    [58, "94"],
    [57, "91"],
    [56, "8F"],
    [55, "8C"],
    [54, "8A"],
    [53, "87"],
    [52, "85"],
    [51, "82"],
    [50, "80"],
    [49, "7D"],
    [48, "7A"],
    [47, "78"],
    [46, "75"],
    [45, "73"],
    [44, "70"],
    [43, "6E"],
    [42, "6B"],
    [41, "69"],
    [40, "66"],
    [39, "63"],
    [38, "61"],
    [37, "5E"],
    [36, "5C"],
    [35, "59"],
    [34, "57"],
    [33, "54"],
    [32, "52"],
    [31, "4F"],
    [30, "4D"],
    [29, "4A"],
    [28, "47"],
    [27, "45"],
    [26, "42"],
    [25, "40"],
    [24, "3D"],
    [23, "3B"],
    [22, "38"],
    [21, "36"],
    [20, "33"],
    [19, "30"],
    [18, "2E"],
    [17, "2B"],
    [16, "29"],
    [15, "26"],
    [14, "24"],
    [13, "21"],
    [12, "1F"],
    [11, "1C"],
    [10, "1A"],
    [9, "17"],
    [8, "14"],
    [7, "12"],
    [6, "0F"],
    [5, "0D"],
    [4, "0A"],
    [3, "08"],
    [2, "05"],
    [1, "03"],
    [0, "00"]
]);

// Example: colorWithAlpha('#FF0000', 20);
export function colorWithAlpha(color, alpha) {
    const xAlpha = alphaHexByPercent.get(alpha);
    if (!xAlpha) {
        throw Error(`Structure preview error: Can't convert alpha value (${alpha}) to hex`);
    }
    return `#${xAlpha}${color.slice(1)}`;
}

const paletteDark = {
    text: {
        primary: "#DEDEDE",
        secondary: "#A4A4A4",
        data: "#579BF9"
    },
    background: {
        topbarData: colorWithAlpha("#3A65E5", 20),
        topbarStandard: colorWithAlpha("#646464", 20),
        buttonInfo: "#579BF9",
        container: "#313131",
        containerFill: "#3E3E3E",
        containerDisabled: "#4F4F4F"
    }
};

const paletteLight = {
    text: {
        primary: "#2F3646",
        secondary: "#6B707B",
        data: "#146FF4"
    },
    background: {
        topbarData: "#DCEEFE",
        topbarStandard: "#F7F7F7",
        buttonInfo: "#146FF4",
        container: "#FFFFFF",
        containerFill: "#F2F2F3",
        containerDisabled: "#C8C8C8"
    }
};

const getLabelContainer = (values, palette) => {
    if (values.labelShowAs === "dynamicText") {
        return {
            type: "Container",
            padding: 8,borders: true,
            children: [
                {
                    type: "Container",
                    padding: 8,
                    children: [
                        {
                            type: "Text",
                            fontSize: 10,
                            fontColor: palette.text.secondary,
                            content: `${
                                values.labelDynamic ? values.labelDynamic : "No dynamic text specified"
                            }`
                        }
                    ]
                }
            ]
        };
    } else {
        return {
            type: "Container",
            padding: 8,borders: true,
            children: [
                {
                    type: "Container",
                    padding: 8,
                    children: [
                        {
                            type: "Text",
                            fontSize: 10,
                            fontColor: palette.text.secondary,
                            content: values.labelAttribute ? `[${values.labelAttribute}]` : "No attribute selected"
                        }
                    ]
                }
            ]
        };
    }
};

const getHeader = (caption, palette) => {
    return {
        type: "Container",
        padding: 8,
        borders: true,
        children: [
            {
                type: "Text",
                bold:true,
                fontSize: 10,
                fontColor: palette.text.secondary,
                content: caption
            }
        ]
    }
}

const getMainLayoutHeaders = (values, palette) => {
    const layout = {
        type: "RowLayout",
        columnSize: "fixed",
        children : []
    }

    layout.children.push(getHeader(`${values.labelShowAs === "attribute" ? "Label (attribute)" : "Label (dynamic text)"}`,palette));

    return layout;
}

const getMainLayoutContent = (values, palette) => {
    const layout = {
        type: "RowLayout",
        columnSize: "fixed",
        borders: true,
        borderWidth: 1,
        children : []
    }

    layout.children.push(getLabelContainer(values,palette));

    return layout;
}

const getTitle = (palette) =>{
    return {
        type: "RowLayout",
        columnSize: "fixed",
            backgroundColor: palette.background.topbarData,
            borders: true,
            borderWidth: 1,
            children : [{
                type: "Container",
                padding: 2,
                children: [{
                    type: "Text",
                    fontColor: palette.text.data,
                    content: 'AuraQ Tree View'
                }]
            }]
    }
}


/**
 * @param {object} values
 * @param {boolean} isDarkMode
 * @param {number[]} version
 * @returns {object}
 */
export function getPreview(values, isDarkMode, version) {
    const palette = isDarkMode ? paletteDark : paletteLight;

    return {
        type: "Container",
        borders: true,
        grow: 1,
        children: [
            getTitle(palette),
            getMainLayoutHeaders(values, palette),
            getMainLayoutContent(values, palette)
        ]
    };
}

/**
 * @param {Object} values
 * @param {("web"|"desktop")} platform
 * @returns {string}
 */
export function getCustomCaption(values, platform) {
    return "AuraQ Tree View";
}
