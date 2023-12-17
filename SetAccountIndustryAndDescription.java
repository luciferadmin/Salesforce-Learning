/** 
* @description       : Set Value in Account Industry field and Description Field.						
* @author            : Rajnish Kumar 
* @group             : Service
* @last modified on  : 17.12.2023
* @last modified by  : Rajnish Kumar 
* Modifications Log
* Ver   Date         Author     Modification
* 1.0   17.12.2023   Rajnish   Initial Version
𝐃𝐞𝐯𝐞𝐥𝐨𝐩 𝐚𝐧 𝐀𝐩𝐞𝐱 𝐓𝐫𝐢𝐠𝐠𝐞𝐫 𝐬𝐨 𝐭𝐡𝐚𝐭 𝐞𝐯𝐞𝐫𝐲 𝐭𝐢𝐦𝐞 𝐰𝐡𝐞𝐧 𝐚𝐧𝐲 𝐚𝐜𝐜𝐨𝐮𝐧𝐭 𝐢𝐬 𝐢𝐧𝐬𝐞𝐫𝐭𝐞𝐝 𝐭𝐡𝐞𝐧 𝐬𝐞𝐭 𝐭𝐡𝐞 𝐯𝐚𝐥𝐮𝐞 𝐨𝐟
𝐭𝐡𝐞 "𝐈𝐧𝐝𝐮𝐬𝐭𝐫𝐲" 𝐟𝐢𝐞𝐥𝐝 𝐭𝐨 "𝐄𝐝𝐮𝐜𝐚𝐭𝐢𝐨𝐧" 𝐢𝐟 𝐈𝐧𝐝𝐮𝐬𝐭𝐫𝐲 𝐟𝐢𝐞𝐥𝐝 𝐢𝐬 𝐛𝐥𝐚𝐧𝐤. 𝐀𝐥𝐬𝐨, 𝐜𝐡𝐞𝐜𝐤 𝐢𝐟 𝐭𝐡𝐞 "𝐃𝐞𝐬𝐜𝐫𝐢𝐩𝐭𝐢𝐨𝐧" 𝐢𝐬 𝐛𝐥𝐚𝐧𝐤 𝐭𝐡𝐞𝐧
𝐬𝐞𝐭 𝐭𝐡𝐞 𝐯𝐚𝐥𝐮𝐞 𝐟𝐨𝐫 𝐭𝐡𝐞 𝐝𝐞𝐬𝐜𝐫𝐢𝐩𝐭𝐢𝐨𝐧 𝐟𝐢𝐞𝐥𝐝 𝐭𝐨 “𝐀𝐜𝐜𝐨𝐮𝐧𝐭 𝐃𝐞𝐬𝐜𝐫𝐢𝐩𝐭𝐢𝐨𝐧 was 𝐛𝐥𝐚𝐧𝐤”
**/
// Trigger to set default values for Industry and Description fields on Account records before insertion
trigger SetAccountIndustryAndDescription on Account (before insert) {
    // Loop through the new Account records being inserted
    for (Account acc : Trigger.new) {
        // Check if Industry is null and set it to 'Education' if true
        if (acc.Industry == null) {
            acc.Industry = 'Education';
        }

        // Check if Description is null and set it to 'Account Description is blank' if true
        if (acc.Description == null) {
            acc.Description = 'Account Description was blank';
        }
    }
}
