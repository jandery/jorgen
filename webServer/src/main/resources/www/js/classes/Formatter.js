/**
 * Purpose of this file is ...
 */
export class Formatter {
    /**
     * Format money as to be shown in UI as integer only.
     * Function devides the argument value with 100 and floors the value to integer and returns as string.
     *
     * Examples:
     * Input 123456 -> Output: "1 234"
     * Input: "265430" -> Output: "2 654"
     * @param moneyInCents
     * @returns {string}
     */
    static formatMoneyAsInt(moneyInCents) {
        const valueAsFloat = moneyInCents / 100;
        const valueAsInt = Math.floor(valueAsFloat);
        return this.formatMoney(valueAsInt);
    }

    /**
     * Format integer as money with whitespace as 1000-separator
     * @param moneyAsInt
     * @return {string}
     */
    static formatMoney(moneyAsInt) {
        return moneyAsInt.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1 ");
    }
}