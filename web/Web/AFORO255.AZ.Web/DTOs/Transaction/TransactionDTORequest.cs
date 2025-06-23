namespace AFORO255.AZ.Web.DTOs.Transaction
{
    public class TransactionDTORequest
    {
        public int accountId { get; set; }
        public decimal amount { get; set; }
        public string type { get; set; }
    }
}
